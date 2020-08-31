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

import static javafx.collections.FXCollections.observableArrayList;

public class WindowDetailsClientController {

    @FXML private TextField txtName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;
    @FXML private CheckBox ckClientVip;

    @FXML private TextField txtRegistration;
    @FXML private TextField txtModel;
    @FXML private TextField txtBrand;
    @FXML private TextField txtColor;
    @FXML private TextArea txtInformation;
    @FXML private ComboBox cbType;



    private Client client;
    private List<TypeVehicle> typeVehicleList = new ArrayList<>();
    private ObservableList<TypeVehicle> typeVehicleObservableList;
    private String plate;
    private String email;

    public void initializeWindow(Client client){
        this.client = client;
        initializeFields();
    }

    public void save(ActionEvent actionEvent) {
        Vehicle vehicle = getVehicle();


        if(vehicle != null){

            this.client = getClient();

            if(this.client != null){
                ClientDAO.updateClient(client,email,"carWash.db");
                VehicleDAO.updateVehicle(client.getVehicle(),plate,"carWash.db");
                back(actionEvent);
            }
            else{
                System.out.println("Client is null");
            }
        }
        else{
            System.out.println("Vehicle is null");
        }

    }

    private void initializeFields(){
        txtName.setText(client.getName());
        txtPhone.setText(client.getPhoneNumber());
        txtEmail.setText(client.getEmail());
        ckClientVip.setSelected(client.isVipClient());
        txtRegistration.setText(client.getVehicle().getRegistrationPlate());
        txtBrand.setText(client.getVehicle().getBrand());
        txtColor.setText(client.getVehicle().getColor());
        txtModel.setText(client.getVehicle().getModel());
        txtInformation.setText(client.getVehicle().getAdditionalInfo());
        fillComboBox(client.getVehicle().getType());
        plate = txtRegistration.getText();
        email = txtEmail.getText();

    }

    private void fillComboBox(TypeVehicle type) {
        typeVehicleList = Arrays.asList(TypeVehicle.values());
        typeVehicleObservableList = observableArrayList(typeVehicleList);
        cbType.setItems(typeVehicleObservableList);
        cbType.setValue(type);
    }

    public void back(ActionEvent actionEvent) {
        Stage stage = (Stage) cbType.getScene().getWindow();
        stage.close();
    }

    private Vehicle getVehicle(){
        Vehicle vehicle = null;
        TypeVehicle type = (TypeVehicle) cbType.getSelectionModel().getSelectedItem();
        String registrationPlate = txtRegistration.getText();
        String brand = txtBrand.getText();
        String color = txtColor.getText();
        String info = txtInformation.getText();
        String model = txtModel.getText();

        if(verifyVehicle(brand,registrationPlate,color,info,model)){
            vehicle = new Vehicle(registrationPlate, color,model,info,type,brand);
        }
        return vehicle;
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

    private Client getClient(){
        Client client = null;
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        Boolean vip = ckClientVip.isSelected();

        if(verifyClient(name,email,phone)){
            client = new Client(name,email,phone,vip,getVehicle());
        }
        return client;
    }

}
