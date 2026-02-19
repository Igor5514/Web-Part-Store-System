
package db_writer;

import Scraper.Vehicle;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javacutils.Pair;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Writer {

    private static final String URL = "jdbc:mysql://localhost:3306/mechanic_car_dealerhip?serverTimezone=UTC";
    private static final String mySQLUser = "root";
    private static final String mySQLPassword = "password";

    private static HikariDataSource dataSource;

    private final ObjectMapper objectMapper = new ObjectMapper();

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(mySQLUser);
        config.setPassword(mySQLPassword);
        config.setMaximumPoolSize(10);
        config.setPoolName("VehiclePool");

        dataSource = new HikariDataSource(config);
    }

    public Writer() {
    }

    public List<Vehicle> readVehicles() {
        File file = new File("src/main/resources/dataset.json");
        try {
            return objectMapper.readValue(file, new TypeReference<List<Vehicle>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sortVehicles() {
        List<Vehicle> vehicles = readVehicles();

//        Set<String> vehicleSet = new HashSet<>();
//        for(Vehicle vehicle : vehicles){
//            vehicleSet.add(vehicle.getMake());
//        }
//        for(String make : vehicleSet){
//            insertMake(make);
//        }

//        Map<String, Integer> modelMap = new HashMap<>();
//
//        for(Vehicle vehicle : vehicles){
//            if(!modelMap.containsKey(vehicle.getModel())){
//                String model = vehicle.getModel();
//                int makeID = getMakeIdByMake(vehicle.getMake());
//                modelMap.put(model, makeID);
//                insertModel(model, makeID);
//            }
//        }

//        Set<String> generationSet = new HashSet<>();
//
//        for(Vehicle vehicle : vehicles){
//            if(generationSet.add(vehicle.getGeneration())){
//                String generation = vehicle.getGeneration();
//                insertGeneration(generation);
//            }
//        }

//        Set<String> engineSet = new HashSet<>();
//
//        for (Vehicle vehicle : vehicles) {
//            String engine =
//                    String.valueOf(vehicle.getEngine()).toLowerCase().trim() + " " +
//                            String.valueOf(vehicle.getEngineFuel()).toLowerCase().trim() + " " +
//                            String.valueOf(vehicle.getTransmission()).toLowerCase().trim();
//
//            if (engineSet.add(engine)) {
//                insertEngine(engine);
//            }
//        }


//        Set<Pair<Integer, Integer>> modelGenerationSet = new HashSet<>();
//        for(Vehicle vehicle : vehicles) {
//            int modelId = getModelIdByModel(vehicle.getModel());
//            int generationId = getGenerationIdByGeneration(vehicle.getGeneration());
//            if(modelGenerationSet.add(Pair.of(modelId,generationId))){
//                insertModelGeneration(modelId, generationId);
//            }
//        }



        for(Vehicle vehicle : vehicles) {
            int modelId = getModelIdByModel(vehicle.getModel());
            int generationId = getGenerationIdByGeneration(vehicle.getGeneration());
            String engine = String.valueOf(vehicle.getEngine()).toLowerCase().trim() + " " +
                            String.valueOf(vehicle.getEngineFuel()).toLowerCase().trim() + " " +
                            String.valueOf(vehicle.getTransmission()).toLowerCase().trim();
            int engineId = getEngineIdByEngine(engine);

            insertGenerationEngine(modelId, generationId, engineId);

        }

    }

    public void insertEngine(String engine) {
        String sql = "INSERT INTO engine (engine) VALUES (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, engine);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting generation: " + e.getMessage());
        }
    }

    public void insertModelGeneration(int model_id, int generation_id) {
        String sql = "INSERT INTO model_generation (model_id, generation_id) VALUES (?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, model_id);
            stmt.setInt(2, generation_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting generation: " + e.getMessage());
        }
    }

    public void insertGenerationEngine(int modelId,int generationId,int engineId) {
        String sql = "INSERT INTO model_generation_engine (model_id, generation_id, engine_id) VALUES (?, ?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, modelId);
            stmt.setInt(2, generationId);
            stmt.setInt(3, engineId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting generation: " + e.getMessage());
        }
    }

    public void insertGeneration(String generation) {
        String sql = "INSERT INTO generation (generation) VALUES (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, generation);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting generation: " + e.getMessage());
        }
    }

    public void insertMake(String make) {
        String sql = "INSERT INTO make (make) VALUES (?)";

        try (Connection conn = dataSource.getConnection(); // Get connection from the pool
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, make);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
        }
    }

    public Integer getIdByQuery(String query, String parameter) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, parameter);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }
        return null;
    }

    public Integer getMakeIdByMake(String make) {
        String query = "SELECT make_id FROM make WHERE make = ?";
        return getIdByQuery(query, make);
    }

    public Integer getModelIdByModel(String model) {
        String query = "SELECT model_id FROM model WHERE model = ?";
        return getIdByQuery(query, model);
    }

    public Integer getGenerationIdByGeneration(String generation) {
        String query = "SELECT generation_id FROM generation WHERE generation = ?";
        return getIdByQuery(query, generation);
    }

    public Integer getEngineIdByEngine(String engine) {
        String query = "SELECT engine_id FROM engine WHERE engine = ?";
        return getIdByQuery(query, engine);
    }
    public void insertModel(String model, int makeId) {
        String sql = "INSERT INTO model (model, make_id) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection(); // Get connection from the pool
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, model);
            stmt.setInt(2, makeId);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Model inserted successfully!");
            } else {
                System.out.println("Failed to insert model.");
            }

        } catch (SQLException e) {
            System.err.println("Error inserting model: " + e.getMessage());
        }
    }
}

