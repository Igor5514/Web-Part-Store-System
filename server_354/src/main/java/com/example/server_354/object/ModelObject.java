package com.example.server_354.object;

public class ModelObject {

    public int model_id;
    public String model;

    public ModelObject(int model_id, String model) {
        this.model_id = model_id;
        this.model = model;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
