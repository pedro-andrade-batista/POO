package model;

import enumerator.TypeService;
import enumerator.TypeVehicle;

import java.time.LocalDate;
import java.util.Objects;

public class Service {
    private int id;
    private double price;
    private TypeService type;

    private Vehicle vehicle;
    private LocalDate registration, conclusion;
    private Client client;

    public Service() {
    }

    public Service(int id, double price, TypeService type, Vehicle vehicle, LocalDate registration, LocalDate conclusion,Client client) {
        this.price = price;
        this.id = id;
        this.type = type;
        this.vehicle = vehicle;
        this.registration = registration;
        this.conclusion = conclusion;
        this.client = client;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeService getType() {
        return type;
    }

    public void setType(TypeService type) {
        this.type = type;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDate registration) {
        this.registration = registration;
    }

    public LocalDate getConclusion() {
        return conclusion;
    }

    public void setConclusion(LocalDate conclusion) {
        this.conclusion = conclusion;
    }

    public TypeVehicle getTypeVehicle(){
        return vehicle.getType();
    }

    public String getPlateVehicle(){
        return vehicle.getRegistrationPlate();
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", price=" + price +
                ", type=" + type +
                ", vehicle=" + vehicle +
                ", registration=" + registration +
                ", conclusion=" + conclusion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return id == service.id &&
                Double.compare(service.price, price) == 0 &&
                type == service.type &&
                Objects.equals(vehicle, service.vehicle) &&
                Objects.equals(registration, service.registration) &&
                Objects.equals(conclusion, service.conclusion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, type, vehicle, registration, conclusion);
    }

    public String getEmailClient() {
        return client.getEmail();
    }
}
