package db_writer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PartTypeWriter {

    private static final String URL = "jdbc:mysql://localhost:3306/mechanic_car_dealerhip?serverTimezone=UTC";
    private static final String mySQLUser = "root";
    private static final String mySQLPassword = "password";

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(mySQLUser);
        config.setPassword(mySQLPassword);
        config.setMaximumPoolSize(10);
        config.setPoolName("VehiclePool");

        dataSource = new HikariDataSource(config);
    }

    public static void main(String[] args) throws IOException {
        iterateOverCarParts();
    }

    public static byte[] convertImageToBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutputStream);
        byte[] imageBytes =  byteArrayOutputStream.toByteArray();
        return imageBytes;
    }

    public static void insertCarPartGroup(String partGroup, byte[] image) {
        String sql = "INSERT INTO car_part_group (group_name, image) values(?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, partGroup);
            stmt.setBytes(2, image);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting column: " + e.getMessage());
        }
    }

    public static void insertCarPart(String partGroup, int groupId, byte[] image) {
        String sql = "INSERT INTO car_part_type (part_type_name,group_id, image) values(?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, partGroup);
            stmt.setInt(2, groupId);
            stmt.setBytes(3,image);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting generation: " + e.getMessage());
        }
    }

    public static Part loadPartGroup(String partGroupName) {
        String sql = "SELECT group_id ,group_name, image FROM car_part_group";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String groupName = rs.getString("group_name");
                    byte[] image = rs.getBytes("image");
                    return new Part(groupName, image);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving part group: " + e.getMessage());
        }
        return null;
    }

    public static int getGroupIdByGroupName(String groupName) {
        String sql = "SELECT group_id FROM car_part_group WHERE group_name = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, groupName);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("group_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching group ID for group name: " + groupName, e);
        }
        return -1;
    }

    public static void iterateOverCarParts() throws IOException {
        File[] images = new File("src/main/resources/image").listFiles();
        Map<String, byte[]> imageHashMap= new HashMap<>();
        Map<String, ArrayList<Part>> partsList = new HashMap<>();
        Map<String, Part> partsGroupMap = new HashMap<>();

        for(File file : images){
            BufferedImage image = ImageIO.read(file);
            imageHashMap.put(file.getName().split("\\.")[0], convertImageToBytes(image));

        }
        try(FileInputStream fileInputStream = new FileInputStream(("src/main/resources/car_parts_list.xlsx"));
            Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            for(Sheet sheet : workbook){
                for(int i=0; i<sheet.getLastRowNum(); i++){
                    Row row = sheet.getRow(i);
                    if(row == null) continue;
                    for(int j = 0; j< Objects.requireNonNull(row).getLastCellNum(); j++){
                        Cell cell = row.getCell(j);

                        if(cell != null) {
                            String cellValue = cell.getStringCellValue();
                            String index = (i+""+j);
                            if(i==0 && !partsGroupMap.containsKey(index)){
                                partsGroupMap.put(index, new Part(cellValue, imageHashMap.get(cellValue)));
                            }
                            String partIndex = (0+""+j);
                            if(i!=0 && !partsList.containsKey(partIndex)){
                                ArrayList<Part> partList = new ArrayList<>();
                                partList.add(new Part(cellValue, imageHashMap.get(cellValue)));
                                partsList.put(partIndex, partList);
                            }else if(i!= 0 && partsList.containsKey(partIndex)){
                                partsList.get(partIndex).add(new Part(cellValue, imageHashMap.get(cellValue)));
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(partsList);

        for(Map.Entry<String, Part> map : partsGroupMap.entrySet()){
            System.out.println("-------------------------------");
            System.out.println(map.getValue().getPartType());
            System.out.println("-------------------------------");
            insertCarPartGroup(map.getValue().getPartType(), map.getValue().getImage());
            if(partsList.containsKey(map.getKey())){

                ArrayList<Part> list = partsList.get(map.getKey());
                System.out.println(list);
                for(int i=0 ; i < list.size(); i++ ){
                    int id = getGroupIdByGroupName(map.getValue().getPartType().trim());
                    Part part = list.get(i);
                    System.out.println((list.get(i)).getPartType().trim());
                    insertCarPart(part.getPartType(), id,part.getImage());
                }
            }
        }
    }
}

