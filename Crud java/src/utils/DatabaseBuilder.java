package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseBuilder {

    public static void main(String[] args) {
        buildBd("carWash.db");
        //dropDataBase("carWash.db");
        //dropTable("carWash.db");
    }

    private static void buildBd(String sourceDbPath) {
        String sqlVehicle = "CREATE TABLE IF NOT EXISTS `vehicle` (" +
                            "`registrationPlate` TEXT PRIMARY KEY," +
                            "`color` TEXT NOT NULL," +
                            "`model` TEXT NOT NULL," +
                            "`additionalInfo` TEXT NOT NULL," +
                            "`typeVehicle` TEXT NOT NULL," +
                            "`brand` TEXT NOT NULL" +
                ")";

        String sqlClient = "CREATE TABLE IF NOT EXISTS `client` (" +
                    "`email` TEXT PRIMARY KEY," +
                    "`name` TEXT NOT NULL," +
                    "`phoneNumber` TEXT NOT NULL,"+
                    "`clientVip` INTEGER NOT NULL," +
                    "`vehiclePlate` TEXT NOT NULL," +
                    "CONSTRAINT `client_vehicle_fk` FOREIGN KEY (vehiclePlate) REFERENCES vehicle (`registrationPlate`)"+
                ")";

        String sqlService = "CREATE TABLE IF NOT EXISTS `service` (" +
                "`id` INTEGER PRIMARY KEY," +
                "`vehiclePlate` TEXT NOT NULL," +
                "`emailClient` TEXT NOT NULL," +
                "`typeService` TEXT NOT NULL,"+
                "`price` INTEGER NOT NULL," +
                "`registration` TEXT NOT NULL," +
                "`conclusion` TEXT NOT NULL," +
                "CONSTRAINT `client_email_fk` FOREIGN KEY (emailClient) REFERENCES client (`email`),"+
                "CONSTRAINT `client_vehicle_fk` FOREIGN KEY (vehiclePlate) REFERENCES vehicle (`registrationPlate`)"+
                ")";


        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sourceDbPath);
            PreparedStatement statement = connection.prepareStatement(sqlVehicle);
            PreparedStatement statement2 = connection.prepareStatement(sqlClient);
            PreparedStatement statement3 = connection.prepareStatement(sqlService)){
            statement.execute();
            statement2.execute();
            statement3.execute();
        }catch (SQLException s){
            s.printStackTrace();
        }
    }
    public static void dropDataBase(String sourcePath){
        String sql = "DROP DATABASE " + sourcePath;
    }

    public static void dropTable(String sourcePath){
        String sql = "DELETE FROM `client`";
    }
}
