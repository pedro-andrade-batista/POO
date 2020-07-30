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
import model.AlarmeColapso;


import java.util.ArrayList;
import java.util.List;

public class CtrlAC {
    @FXML
    private TableView tableAC;
    @FXML private TableColumn<AlarmeColapso,Integer> tcIDAC;
    @FXML private TableColumn<AlarmeColapso,String> tcStatus;
    @FXML private Label lbQuantidadeAC;
    @FXML private Button btnFechar;

    ObservableList<AlarmeColapso> listaAlarmaColapso;
    int contadorId = 1;
    List<AlarmeColapso> tabledata = new ArrayList<>();

    public void addTableAC(ActionEvent actionEvent) {
        bindTableAC();
        listaAlarmaColapso = FXCollections.observableList(tabledata);
        tableAC.setItems(listaAlarmaColapso);
        fill();
        atualizalabel();
        contadorId++;
    }

    public void bindTableAC(){
        tcIDAC.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void fill() {
        String status = "Não há sinal de evacuação";
        AlarmeColapso e = new AlarmeColapso(contadorId, status);
        tabledata.add(e);
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) btnFechar.getScene().getWindow();
        stage.close();
    }

    public void removeItem(ActionEvent actionEvent) {
        AlarmeColapso remove = (AlarmeColapso) tableAC.getSelectionModel().getSelectedItem();
        listaAlarmaColapso.remove(remove);
        atualizalabel();
    }

    public void atualizalabel(){
        String text = Integer.toString(listaAlarmaColapso.size());
        String label = lbQuantidadeAC.getText();
        lbQuantidadeAC.setText("Quantidade: " + tableAC.getItems().size());

    }
}
