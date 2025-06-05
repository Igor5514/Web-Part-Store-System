package com.example.server_354.object;

public class VehicleComponent {

    public String vehicleComponentType;
    public String vehicleComponentValue;

    public VehicleComponent(String vehicleComponentType, String vehicleComponentValue) {
        this.vehicleComponentType = vehicleComponentType;
        this.vehicleComponentValue = vehicleComponentValue;
    }

    public VehicleComponent() {
    }

    public String getVehicleComponentType() {
        return vehicleComponentType;
    }

    public void setVehicleComponentType(String vehicleComponentType) {
        this.vehicleComponentType = vehicleComponentType;
    }

    public String getVehicleComponentValue() {
        return vehicleComponentValue;
    }

    public void setVehicleComponentValue(String vehicleComponentValue) {
        this.vehicleComponentValue = vehicleComponentValue;
    }
}
