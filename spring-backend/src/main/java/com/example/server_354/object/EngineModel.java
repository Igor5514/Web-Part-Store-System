package com.example.server_354.object;

public class EngineModel {
    private String model;
    private String generation;

    public EngineModel(String model, String generation) {
        this.model = model;
        this.generation = generation;
    }

    public String getModel() {
        return model;
    }

    public String getGeneration() {
        return generation;
    }
}
