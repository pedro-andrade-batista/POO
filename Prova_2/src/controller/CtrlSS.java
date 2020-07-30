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
import model.Barragem;
import model.Dispositivos;
import model.SistemaSismo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class CtrlSS extends Observable {
    @FXML private TableView tableSS;
    @FXML private TableColumn<SistemaSismo,Integer> tcID;
    @FXML private TableColumn<SistemaSismo,Boolean> tcVariavelBinaria;
    @FXML private TableColumn<SistemaSismo,String> tcStatus;
    @FXML private Label lbQuantidadeSS;
    @FXML private Button btnFechar;

    Barragem aux;
    ObservableList<SistemaSismo> lista;
    int contador = 1;
    List<SistemaSismo> tabledata = new ArrayList<>();

    public void bindTableSS(){
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcVariavelBinaria.setCellValueFactory(new PropertyValueFactory<>("varBinaria"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void addtable(ActionEvent actionEvent) {
        bindTableSS();
        lista = FXCollections.observableList(tabledata);
        tableSS.setItems(lista);
        fill();
    }

    public void fill() {
        String status = "Não há abalos no terreno";
        boolean booleanAleatorio = booleanAleatory();
        if(booleanAleatorio == true){
            status = "Há abalos no terreno";
        }

        SistemaSismo e = new SistemaSismo(contador,booleanAleatorio, status);
        aux.addDispositivos(e);

        tabledata.add(e);
        atualizalabel();
        contador++;
    }

    private boolean booleanAleatory(){
        Random gerador = new Random();
        return (gerador.nextInt(2) == 1) ? true : false;
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) btnFechar.getScene().getWindow();
        stage.close();
    }


    public void removeitem(ActionEvent actionEvent) {
        SistemaSismo elemento = (SistemaSismo) tableSS.getSelectionModel().getSelectedItem();
        lista.remove(elemento);
        aux.removeDispositivos(elemento);
        atualizalabel();
    }

    public void atualizalabel(){
        String text = Integer.toString(lista.size());
        String label = lbQuantidadeSS.getText();
        lbQuantidadeSS.setText("Quantidade: " + tableSS.getItems().size());

    }


    public Barragem show(Barragem b) {

        this.aux = b;
        for (Dispositivos d : b.getDispositivos()) {
            if(d instanceof SistemaSismo){
                System.out.println("d = " + d);
                SistemaSismo e = new SistemaSismo(d.getId(), ((SistemaSismo) d).isVarBinaria(), ((SistemaSismo) d).getStatus());
                tabledata.add(e);
            }
        }
        return aux;
    }
}
