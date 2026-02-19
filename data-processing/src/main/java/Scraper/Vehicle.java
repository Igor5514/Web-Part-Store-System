package Scraper;

public class Vehicle {
    private String make;
    private String model;
    private String generation;
    private String engine;
    private String driveWheel;
    private String engineType;
    private String fuelType;
    private String power;
    private String topSpeed;
    private String acceleration;
    private String cylinders;
    private String valvesPerCyl;
    private String engineCapacity;
    private String engineFuel;
    private String transmission;

    public Vehicle(){

    }

    public Vehicle(String make, String model, String generation, String engine, String driveWheel, String engineType,
                   String fuelType, String power, String topSpeed, String acceleration, String cylinders,
                   String valvesPerCyl, String engineCapacity, String engineFuel, String transmission) {
        this.make = make;
        this.model = model;
        this.generation = generation;
        this.engine = engine;
        this.driveWheel = driveWheel;
        this.engineType = engineType;
        this.fuelType = fuelType;
        this.power = power;
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.cylinders = cylinders;
        this.valvesPerCyl = valvesPerCyl;
        this.engineCapacity = engineCapacity;
        this.engineFuel = engineFuel;
        this.transmission = transmission;
    }

    // Getters
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getGeneration() {
        return generation;
    }

    public String getEngine() {
        return engine;
    }

    public String getDriveWheel() {
        return driveWheel;
    }

    public String getEngineType() {
        return engineType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getPower() {
        return power;
    }

    public String getTopSpeed() {
        return topSpeed;
    }

    public String getAcceleration() {
        return acceleration;
    }

    public String getCylinders() {
        return cylinders;
    }

    public String getValvesPerCyl() {
        return valvesPerCyl;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public String getEngineFuel() {
        return engineFuel;
    }

    public String getTransmission() {
        return transmission;
    }

    // Setters
    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setDriveWheel(String driveWheel) {
        this.driveWheel = driveWheel;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setTopSpeed(String topSpeed) {
        this.topSpeed = topSpeed;
    }

    public void setAcceleration(String acceleration) {
        this.acceleration = acceleration;
    }

    public void setCylinders(String cylinders) {
        this.cylinders = cylinders;
    }

    public void setValvesPerCyl(String valvesPerCyl) {
        this.valvesPerCyl = valvesPerCyl;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public void setEngineFuel(String engineFuel) {
        this.engineFuel = engineFuel;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", generation='" + generation + '\'' +
                ", engine='" + engine + '\'' +
                ", driveWheel='" + driveWheel + '\'' +
                ", engineType='" + engineType + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", power='" + power + '\'' +
                ", topSpeed='" + topSpeed + '\'' +
                ", acceleration='" + acceleration + '\'' +
                ", cylinders='" + cylinders + '\'' +
                ", valvesPerCyl='" + valvesPerCyl + '\'' +
                ", engineCapacity='" + engineCapacity + '\'' +
                ", engineFuel='" + engineFuel + '\'' +
                ", transmission='" + transmission + '\'' +
                '}';
    }
}
