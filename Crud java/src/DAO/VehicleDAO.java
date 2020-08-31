package DAO;

import enumerator.TypeVehicle;
import model.Client;
import model.Vehicle;

import java.sql.*;

public class VehicleDAO {
    public static void save(Vehicle vehicle, String sourcePath){
        String sql = "INSERT INTO `vehicle` (registrationPlate, color, model, additionalInfo, typeVehicle, brand) VALUES(?,?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,vehicle.getRegistrationPlate());
            preparedStatement.setString(2,vehicle.getColor());
            preparedStatement.setString(3,vehicle.getModel());
            preparedStatement.setString(4,vehicle.getAdditionalInfo());
            preparedStatement.setString(5,vehicle.getType().toString());
            preparedStatement.setString(6,vehicle.getBrand());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static Vehicle getVehicle(String plate, String sourcePath){
        String sql = "SELECT * FROM `vehicle` WHERE registrationPlate = ?";
        Vehicle vehicle = null;
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,plate);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            if(resultSet.next()){
                String registrationPlate = resultSet.getString("registrationPlate");
                String color = resultSet.getString("color");
                String model = resultSet.getString("model");
                String info = resultSet.getString("additionalInfo");
                String type = resultSet.getString("typeVehicle");
                TypeVehicle typeVehicle = TypeVehicle.valueOf(type);
                String brand = resultSet.getString("brand");
                vehicle = new Vehicle(registrationPlate,color,model,info,typeVehicle,brand);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vehicle;
    }

    public static void updateVehicle(Vehicle vehicle, String plate, String sourcePath){

        String sql = "UPDATE `vehicle` SET `registrationPlate` = ?, `color` = ?, `model` = ?, `additionalInfo` = ?, `typeVehicle` = ?, " +
                "`brand` = ? WHERE `registrationPlate` = ?";
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,vehicle.getRegistrationPlate());
            preparedStatement.setString(2,vehicle.getColor());
            preparedStatement.setString(3,vehicle.getModel());
            preparedStatement.setString(4,vehicle.getAdditionalInfo());
            preparedStatement.setString(5,vehicle.getType().toString());
            preparedStatement.setString(6,vehicle.getBrand());
            preparedStatement.setString(7,plate);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteVehicle(String vehiclePlate, String sourcePath){
        String sql = "DELETE FROM `vehicle` WHERE registrationPlate = ?";
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,vehiclePlate);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
