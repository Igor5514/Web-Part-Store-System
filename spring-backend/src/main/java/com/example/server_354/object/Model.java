package com.example.server_354.object;

import jakarta.persistence.*;

@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int model_id;
    private String model;

    @ManyToOne
    @JoinColumn(name = "make_id")
    private Make make;

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
