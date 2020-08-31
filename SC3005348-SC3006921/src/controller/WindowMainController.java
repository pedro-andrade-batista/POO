package controller;

import DAO.ClientDAO;
import DAO.ServicesDAO;
import DAO.VehicleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import loader.WindowAddClientLoader;
import loader.WindowDetailsClientLoader;
import loader.WindowServiceLoader;
import model.Client;
import model.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WindowMainController {

    @FXML private TableView<Client> tableMain;
    @FXML private TableColumn<Client,String> cName;
    @FXML private TableColumn<Client,String> cEmail;
    @FXML private TableColumn<Client,String> cPhoneNumber;
    @FXML private TableColumn<Client,Boolean> cClientVip;

    @FXML private TextField txtFilterTable;
    @FXML private CheckBox ckFilterVip;


    private List<Client> dataTable = new ArrayList<>();;
    private ObservableList<Client> observableList;

    @FXML
    public void initialize(){
        bindTable();
        tableMain.refresh();
    }

    public void bindTable(){
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        cClientVip.setCellValueFactory(new PropertyValueFactory<>("vipClient"));
        dataTable = ClientDAO.getAll("carWash.db");
        observableList = FXCollections.observableList(dataTable);
        tableMain.setItems(observableList);

    }

    public void filter(KeyEvent keyEvent) {
        FilteredList<Client> filteredData = new FilteredList<>(observableList, c -> true);
        if(!txtFilterTable.getText().equals("")){
            filteredData.setPredicate(client -> {
                if (client.getName().toLowerCase().contains(txtFilterTable.getText())) {
                    return true; // Filter matches first name.
                } else if (client.getEmail().toLowerCase().contains(txtFilterTable.getText())) {
                    return true; // Filter matches last name.
                } else if (client.getPhoneNumber().contains(txtFilterTable.getText())) {
                    return true;
                }else{
                    return false;
                }
            });
        }
        tableMain.setItems(filteredData);
    }

    public void filterCheck(){

        FilteredList<Client> filteredData = new FilteredList<>(observableList, c -> true);

        filteredData.setPredicate(client -> {
            if (client.isVipClient() == ckFilterVip.isSelected()) {
                return true; // Filter matches first name.
            }else{
                return false;
            }
        });
        tableMain.setItems(filteredData);
    }


    public void showAddClient(){
        WindowAddClientLoader view = new WindowAddClientLoader();
        view.start(tableMain);
    }

    public void showDetails(ActionEvent actionEvent) {
        WindowDetailsClientLoader view = new WindowDetailsClientLoader();
        Client client = tableMain.getSelectionModel().getSelectedItem();

        if(client != null && client.getVehicle() != null){
            view.start(client);
            tableMain.getItems().clear();
            tableMain.getItems().setAll(ClientDAO.getAll("carWash.db"));
        }
    }


    public void showWindowServices(ActionEvent actionEvent) {
       WindowServiceLoader loader = new WindowServiceLoader();
       Client client = tableMain.getSelectionModel().getSelectedItem();
       if(client != null)
           loader.start(client);
    }

    public void remove(ActionEvent actionEvent) {
        Client client = tableMain.getSelectionModel().getSelectedItem();
        if(client != null){

            ServicesDAO.deleteService(client.getEmail(),"carWash.db");
            VehicleDAO.deleteVehicle(client.getVehicle().getRegistrationPlate(),"carWash.db");
            ClientDAO.deleteClient(client.getEmail(),"carWash.db");

            observableList.remove(client);
            tableMain.getItems().clear();
            tableMain.getItems().setAll(ClientDAO.getAll("carWash.db"));
        }
    }

}
