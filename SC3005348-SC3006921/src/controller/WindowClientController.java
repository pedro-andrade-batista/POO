package controller;

import DAO.ClientDAO;
import DAO.VehicleDAO;
import enumerator.TypeVehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Client;
import model.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javafx.collections.FXCollections.*;

public class WindowClientController {

    @FXML private TextField txtName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhoneNumber;
    @FXML private CheckBox ckClientVip;

    @FXML private TextField txtRegistrationPlate;
    @FXML private TextField txtBrand;
    @FXML private TextField txtColor;
    @FXML private TextArea txtInfo;
    @FXML private ComboBox cbTypeVehicle;
    @FXML private TextField txtModel;

    private List<Client> lista = new ArrayList<>();
    private List<TypeVehicle> vehicleList = new ArrayList<>();
    private ObservableList<TypeVehicle> observableList;

    private TableView<Client> table;
    private ObservableList observableList2;

    public void initializeWindow(TableView<Client> tableView){
        initializeTable(tableView);
        fillComboBox();
    }



    public void initializeTable(TableView<Client> tableView){
        table = tableView;
        observableList2 = FXCollections.observableArrayList(tableView.getItems());
        table.setItems(observableList2);
    }


    public void fillComboBox(){

        vehicleList = Arrays.asList(TypeVehicle.values());
        observableList = observableArrayList(vehicleList);
        cbTypeVehicle.setItems(observableList);
        cbTypeVehicle.setValue(vehicleList.get(0));
    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) cbTypeVehicle.getScene().getWindow();
        stage.close();
    }

    public void save(ActionEvent actionEvent) {
        Vehicle vehicle = getVehicle();
        if(vehicle != null){
            Client client = getClient();
            if(client != null){

                this.observableList2.add(client);
                VehicleDAO.save(vehicle,"carWash.db");
                ClientDAO.save(client,"carWash.db");
                clearFields();
            }
            else{
                System.out.println("Client is null");
            }
        }
        else{
            System.out.println("Vehicle is null");
        }

    }


    private void clearFields() {
        txtName.setText("");
        txtModel.setText("");
        txtInfo.setText("");
        txtColor.setText("");
        txtBrand.setText("");
        txtEmail.setText("");
        txtPhoneNumber.setText("");
        txtRegistrationPlate.setText("");
    }

    private Vehicle getVehicle(){
        Vehicle vehicle = null;
        TypeVehicle type = (TypeVehicle) cbTypeVehicle.getSelectionModel().getSelectedItem();
        String registrationPlate = txtRegistrationPlate.getText();
        String brand = txtBrand.getText();
        String color = txtColor.getText();
        String info = txtInfo.getText();
        String model = txtModel.getText();

        if(verifyVehicle(brand,registrationPlate,color,info,model)){
            vehicle = new Vehicle(registrationPlate, color,model,info,type,brand);
        }
        return vehicle;
    }

    private Client getClient(){
        Client client = null;
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhoneNumber.getText();
        Boolean vip = ckClientVip.isSelected();

        if(verifyClient(name,email,phone)){
            client = new Client(name,email,phone,vip,getVehicle());
        }
        return client;
    }

    private Boolean verifyVehicle(String brand, String registration, String color, String info, String model) {
        if(brand == null || registration == null || color == null || info == null || model == null)
            return false;
        else if(brand.equals("") || registration.equals("") || color.equals("") || info.equals("") || model.equals(""))
            return false;
        else
            return true;
    }

    private Boolean verifyClient(String name, String email, String phone) {
        if(name == null || email == null || phone == null )
            return false;
        else if(name.equals("") || email.equals("") || phone.equals(""))
            return false;
        else
            return true;
    }

}
