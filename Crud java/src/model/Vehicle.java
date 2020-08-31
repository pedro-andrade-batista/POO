package model;

import enumerator.TypeVehicle;

public class Vehicle {
    private String registrationPlate;
    private String color;
    private String model;
    private String additionalInfo;
    private TypeVehicle type;
    private String brand;

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationPlate='" + registrationPlate + '\'' +
                ", color='" + color + '\'' +
                ", model='" + model + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", type=" + type +
                ", brand='" + brand + '\'' +
                '}';
    }

    public Vehicle(String registrationplate, String color, String model, String additionalInfo, TypeVehicle type, String brand) {
        this.registrationPlate = registrationplate;
        this.color = color;
        this.model = model;
        this.additionalInfo = additionalInfo;
        this.type = type;
        this.brand = brand;
    }

    public Vehicle() {
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public TypeVehicle getType() {
        return type;
    }

    public void setType(TypeVehicle type) {
        this.type = type;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
