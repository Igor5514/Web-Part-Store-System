package com.example.server_354.object;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int engine_id;
    private String engine;

    public Engine(int engine_id, String engine) {
        this.engine_id = engine_id;
        this.engine = engine;
    }

    public Engine() {
    }

    public int getEngine_id() {
        return engine_id;
    }

    public void setEngine_id(int engine_id) {
        this.engine_id = engine_id;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "engine_id=" + engine_id +
                ", engine=" + engine +
                '}';
    }
}
