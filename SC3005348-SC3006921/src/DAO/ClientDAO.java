package DAO;

import enumerator.TypeVehicle;
import model.Client;
import model.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public static List<Client> getAll(String sourcePath){
        List<Client> clientList = new ArrayList<>();

        String sql = "SELECT * FROM `client`";

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phoneNumber");
                int clientVip = resultSet.getInt("clientVip");
                boolean isVip = clientVip == 1;
                String vehiclePlate = resultSet.getString("vehiclePlate");
                Vehicle vehicle = VehicleDAO.getVehicle(vehiclePlate,"carWash.db");
                Client client = new Client(name, email, phone, isVip, vehicle);
                clientList.add(client);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clientList;
    }

    public static void save(Client client, String sourcePath){
        String sql = "INSERT INTO `client` (email, name, phoneNumber, clientVip, vehiclePlate) VALUES(?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,client.getEmail());
            preparedStatement.setString(2,client.getName());
            preparedStatement.setString(3,client.getPhoneNumber());
            preparedStatement.setInt(4,client.isVipClient() ? 1 : 0);
            preparedStatement.setString(5,client.getVehicle().getRegistrationPlate());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public static Client getClient(String email, String sourcePath){
        String sql = "SELECT * FROM `client` WHERE email = ?";
        Client client = null;
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,email);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            if(resultSet.next()){
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phoneNumber");
                int clientVip = resultSet.getInt("clientVip");
                boolean isVip = clientVip == 1;
                String vehiclePlate = resultSet.getString("vehiclePlate");
                Vehicle vehicle = VehicleDAO.getVehicle(vehiclePlate,"carWash.db");
                client = new Client(name, email, phone, isVip, vehicle);
;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client;

    }

    public static void updateClient(Client client,String email, String sourcePath){
        String sql = "UPDATE client SET email = ?, name = ?, phoneNumber = ?, clientVip = ?, vehiclePlate = ? " +
                    "WHERE email = ?";
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,client.getEmail());
            preparedStatement.setString(2,client.getName());
            preparedStatement.setString(3,client.getPhoneNumber());
            preparedStatement.setInt(4,client.isVipClient() ? 1 : 0);
            preparedStatement.setString(5,client.getVehicle().getRegistrationPlate());
            preparedStatement.setString(6,email);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteClient(String email,String sourcePath){
        String sql = "DELETE FROM `client` WHERE email = ?";

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourcePath);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,email);
            preparedStatement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
