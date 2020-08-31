package DAO;

import enumerator.TypeService;
import model.Client;
import model.Service;
import model.Vehicle;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServicesDAO {
    public static void save(Service service, String sourcePath){
        String sql = "INSERT INTO `service` (id, vehiclePlate, emailClient, typeService, price, registration,conclusion) VALUES(?,?,?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1,service.getId());
            preparedStatement.setString(2,service.getPlateVehicle());
            preparedStatement.setString(3,service.getEmailClient());
            preparedStatement.setString(4,service.getType().toString());
            preparedStatement.setDouble(5,service.getPrice());
            preparedStatement.setString(6,service.getRegistration().toString());
            preparedStatement.setString(7,service.getConclusion().toString());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Service> getAll(String email,String sourcePath) {
        List<Service> serviceList = new ArrayList<>();

        String sql = "SELECT * FROM `service` WHERE emailClient = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,email);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Double price = resultSet.getDouble("price");
                String vehiclePlate = resultSet.getString("vehiclePlate");
                String type = resultSet.getString("typeService");
                TypeService typeVehicle = TypeService.valueOf(type);


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String registration = resultSet.getString("registration");
                LocalDate registrationConverted = LocalDate.parse(registration,formatter);
                String conclusion = resultSet.getString("conclusion");
                LocalDate conclusionConverted = LocalDate.parse(conclusion,formatter);

                Vehicle vehicle = VehicleDAO.getVehicle(vehiclePlate,sourcePath);
                Client client = ClientDAO.getClient(email,sourcePath);

                Service service = new Service(id,price,typeVehicle,vehicle,registrationConverted,conclusionConverted,client);

                serviceList.add(service);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return serviceList;
    }

    public static void deleteService(String email, String sourcePath){
        String sql = "DELETE FROM `service` WHERE emailClient = ?";

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,email);
            preparedStatement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteService(int id, String sourcePath){
        String sql = "DELETE FROM `service` WHERE id = ?";

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateService(Service service,int id, String sourcePath){
        String sql = "UPDATE service SET id = ?, vehiclePlate = ?, emailClient = ?, typeService = ?, price = ?, registration = ?, conclusion = ? " +
                "WHERE id = ?";
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,service.getId());
            preparedStatement.setString(2,service.getPlateVehicle());
            preparedStatement.setString(3,service.getEmailClient());
            preparedStatement.setString(4,service.getType().toString());
            preparedStatement.setDouble(5,service.getPrice());
            preparedStatement.setString(6,service.getRegistration().toString());
            preparedStatement.setString(7,service.getConclusion().toString());
            preparedStatement.setInt(8,id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
