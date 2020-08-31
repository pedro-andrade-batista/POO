package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Client {
    private String name, email;
    private String phoneNumber;
    private boolean vipClient;
    private List<Service> services = new ArrayList<>();
    private Vehicle vehicle;


    public Client() {
    }


    public Client(String name, String email, String phoneNumber, boolean vipClient, Vehicle vehicle) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.vipClient = vipClient;
        this.vehicle = vehicle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isVipClient() {
        return vipClient;
    }

    public void setVipClient(boolean vipClient) {
        this.vipClient = vipClient;
    }

    public void addService(Service service){
        services.add(service);
    }


    public Iterator<Service> iteratorServices(){
        return services.iterator();
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return vipClient == client.vipClient &&
                Objects.equals(name, client.name) &&
                Objects.equals(email, client.email) &&
                Objects.equals(phoneNumber, client.phoneNumber) &&
                Objects.equals(vehicle, client.vehicle);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber, vipClient, vehicle);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", vipClient=" + vipClient +
                ", services=" + services +
                ", vehicle=" + vehicle +
                '}';
    }
}
