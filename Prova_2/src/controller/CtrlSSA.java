package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.SistemaSismo;
import model.SistemaSismoAlarme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CtrlSSA {
    @FXML private TableView tableSSA;
    @FXML private TableColumn<SistemaSismoAlarme,Integer> tcID;
    @FXML private TableColumn<SistemaSismoAlarme,Boolean> tcAbalo;
    @FXML private TableColumn<SistemaSismoAlarme,String> tcStatus;
    @FXML private Label lbQuantidadeSSA;
    @FXML private Button btnFechar;

    ObservableList<SistemaSismoAlarme> listaSismoAlarme;
    int contadorId = 1;
    List<SistemaSismoAlarme> tabledata = new ArrayList<>();

    public void addtableSSA(ActionEvent actionEvent) {
        bindTableSSA();
        listaSismoAlarme = FXCollections.observableList(tabledata);
        tableSSA.setItems(listaSismoAlarme);
        fill();
        atualizalabel();
        contadorId++;
    }

    public void bindTableSSA(){
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcAbalo.setCellValueFactory(new PropertyValueFactory<>("abalo"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void fill() {
        double doubleAleatorio = doubleAleatory();
        double doubleFormatado = Math.round(doubleAleatorio * 100) / 100d;
        String status = "Não há perigo na area";
        SistemaSismoAlarme auxiliar = new SistemaSismoAlarme();
        if(doubleAleatorio >= auxiliar.getSeguranca())
            status = "Perigo eminente na area";
        SistemaSismoAlarme e = new SistemaSismoAlarme(contadorId,doubleFormatado, status);
        tabledata.add(e);

    }

    private double doubleAleatory(){
        Random gerador = new Random();
        return gerador.nextDouble() * 10;
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) btnFechar.getScene().getWindow();
        stage.close();
    }

    public void removeitem(ActionEvent actionEvent) {
        SistemaSismoAlarme remove = (SistemaSismoAlarme) tableSSA.getSelectionModel().getSelectedItem();
        listaSismoAlarme.remove(remove);
        atualizalabel();
    }

    public void atualizalabel(){
        String text = Integer.toString(listaSismoAlarme.size());
        String label = lbQuantidadeSSA.getText();
        lbQuantidadeSSA.setText("Quantidade: " + tableSSA.getItems().size());

    }
}
