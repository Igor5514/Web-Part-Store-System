package com.example.server_354.object;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class VehicleComponent {

    private String make;
    private boolean makeValueExist;
    private String model;
    private boolean modelValueExist;
    private String generation;
    private boolean generationValueExist;
    private String engine;
    private boolean engineValueExist;

    public VehicleComponent(String make, boolean makeValueExist, String model, boolean modelValueExist, String generation, boolean generationValueExist, String engine, boolean engineValueExist) {
        this.make = make;
        this.makeValueExist = makeValueExist;
        this.model = model;
        this.modelValueExist = modelValueExist;
        this.generation = generation;
        this.generationValueExist = generationValueExist;
        this.engine = engine;
        this.engineValueExist = engineValueExist;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public boolean isMakeValueExist() {
        return makeValueExist;
    }

    public void setMakeValueExist(boolean makeValueExist) {
        this.makeValueExist = makeValueExist;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isModelValueExist() {
        return modelValueExist;
    }

    public void setModelValueExist(boolean modelValueExist) {
        this.modelValueExist = modelValueExist;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public boolean isGenerationValueExist() {
        return generationValueExist;
    }

    public void setGenerationValueExist(boolean generationValueExist) {
        this.generationValueExist = generationValueExist;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public boolean isEngineValueExist() {
        return engineValueExist;
    }

    public void setEngineValueExist(boolean engineValueExist) {
        this.engineValueExist = engineValueExist;
    }

    @Override
    public String toString() {
        return "VehicleComponent{" +
                "make='" + make + '\'' +
                ", makeValueExist=" + makeValueExist +
                ", model='" + model + '\'' +
                ", modelValueExist=" + modelValueExist +
                ", generation='" + generation + '\'' +
                ", generationValueExist=" + generationValueExist +
                ", engine='" + engine + '\'' +
                ", engineValueExist=" + engineValueExist +
                '}';
    }
}
