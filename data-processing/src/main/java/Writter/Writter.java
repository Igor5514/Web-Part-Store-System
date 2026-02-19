package Writter;

import Scraper.Vehicle;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Writter {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);
    public Writter() {
    }

    public static void writeVehicle(Vehicle vehicle) {
        File file = new File("src/main/resources/dataset2.json");
        List<Vehicle> cars = new ArrayList<>();

        try {
            if (file.exists() && file.length() > 0) {
                cars = objectMapper.readValue(file, new TypeReference<>() {
                });
            }
            cars.add(vehicle);

            objectMapper.writeValue(file, cars);
            System.out.println("Vehicle: "+vehicle.getMake()+" | "+vehicle.getModel()+" | "+vehicle.getEngine());
        } catch (IOException e) {
            System.err.println("Error handling JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
