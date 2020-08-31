package controller;

import DAO.ServicesDAO;
import enumerator.TypeService;
import enumerator.TypeVehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Client;
import model.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WindowServicesController {

    @FXML private TableView<Service> tableService;
    @FXML private TableColumn<Service,Integer> cPrice;
    @FXML private TableColumn<Service, TypeVehicle> cType;
    @FXML private TableColumn<Service,String> cPlateVehicle;
    @FXML private TableColumn<Service, LocalDate> cRegistration;
    @FXML private TableColumn<Service,LocalDate> cConclusion;
    @FXML private TableColumn<Service,Integer>cId;

    @FXML private TextField txtPrice;
    @FXML private ComboBox cbTypeService;
    @FXML private DatePicker dpRegistration;
    @FXML private DatePicker dpConclusion;

    private ObservableList<Service> observableList;
    private List<Service> serviceList = new ArrayList<>();
    private Client client;

    private List<TypeService> typeServices = new ArrayList<>();
    private ObservableList<TypeService> typeServiceObservableList;

    private int id;

    public void initializeTable(Client client){
        this.client = client;
        bindTable();
        initializeTable();
        fillComboBox();
        fillFieldsInitialize();

    }

    private void fillFieldsInitialize() {
        dpConclusion.setValue(LocalDate.now());
        dpRegistration.setValue(LocalDate.now());

    }


    private void fillComboBox() {
        typeServices = Arrays.asList(TypeService.values());
        typeServiceObservableList = FXCollections.observableArrayList(typeServices);
        cbTypeService.setItems(typeServiceObservableList);
        cbTypeService.setValue(TypeService.COMPLETE);
    }

    private Service getService() {
        Service service = null;
        id++;

        TypeService typeServiceComboBox = (TypeService) cbTypeService.getSelectionModel().getSelectedItem();
        LocalDate registration = dpRegistration.getValue();
        LocalDate conclusion = dpConclusion.getValue();

        Double price = tryDouble(txtPrice.getText());
        service = new Service(id,price,typeServiceComboBox,client.getVehicle(),registration,conclusion,client);

        return service;

    }

    public void bindTable(){
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        cType.setCellValueFactory(new PropertyValueFactory<>("type"));
        cPlateVehicle.setCellValueFactory(new PropertyValueFactory<>("plateVehicle"));
        cRegistration.setCellValueFactory(new PropertyValueFactory<>("registration"));
        cConclusion.setCellValueFactory(new PropertyValueFactory<>("conclusion"));

    }

    public void initializeTable(){
        serviceList = ServicesDAO.getAll(client.getEmail(),"carWash.db");
        id = serviceList.size();
        observableList = FXCollections.observableArrayList(serviceList);
        tableService.setItems(observableList);
    }

    public void save(ActionEvent actionEvent) {
        Service service = getService();
        if(service != null){
            client.addService(service);
            observableList.add(service);
            ServicesDAO.save(service,"carWash.db");
            clearFields();
        }
        else{
            System.out.println("Service is null");
        }
    }

    private void clearFields(){
        txtPrice.setText("");
        dpConclusion.setValue(LocalDate.now());
        dpRegistration.setValue(LocalDate.now());
    }

    public void back(ActionEvent actionEvent) {
        Stage stage = (Stage) cbTypeService.getScene().getWindow();
        stage.close();
    }

    public void edit(ActionEvent actionEvent) {
        Service service = tableService.getSelectionModel().getSelectedItem();
        if(service != null){
            modifyService(service);
            ServicesDAO.updateService(service,service.getId(),"carWash.db");
            tableService.refresh();
        }
    }

    private void fillFields(Service service) {
        txtPrice.setText(Double.toString(service.getPrice()));
        cbTypeService.setValue(service.getType());
        dpRegistration.setValue(service.getRegistration());
        dpConclusion.setValue(service.getConclusion());

    }

    private void modifyService(Service service){
        service.setPrice(Double.parseDouble(txtPrice.getText()));
        service.setType((TypeService) cbTypeService.getSelectionModel().getSelectedItem());
        service.setConclusion(dpConclusion.getValue());
        service.setRegistration(dpRegistration.getValue());
    }

    @FXML
    public void clickItem(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2){
            Service service = tableService.getSelectionModel().getSelectedItem();
            if(service != null){
                fillFields(service);
            }
        }
    }

    public void remove(ActionEvent actionEvent) {
        Service service = tableService.getSelectionModel().getSelectedItem();
        if(service != null){
            observableList.remove(service);
            ServicesDAO.deleteService(service.getId(),"carWash.db");
        }
    }

    private Double tryDouble(String price){
        try{
            return Double.parseDouble(price);
        } catch (NumberFormatException e) {
            return 0.0;
        }

    }
}
