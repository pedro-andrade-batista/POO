package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import loaders.WindowBarragem;
import model.Barragem;
import model.SistemaSismo;

import java.util.ArrayList;
import java.util.List;

public class CtrlPeixeVivo {

    @FXML private TextField inputName;
    @FXML private TextField inputCity;
    @FXML private TableView tableBarragem;
    @FXML private TableColumn<Barragem,String> cNome;
    @FXML private TableColumn<Barragem,String> cCidade;
    @FXML private Button close;


    @FXML

    public void initialize(){
        bindTable();

    }


    List<Barragem> barragem = new ArrayList<>();
    ObservableList<Barragem> observableList;

    public void bindTable(){
        cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
    }


    public void addBarragem(ActionEvent actionEvent) {
        observableList = FXCollections.observableList(barragem);
        tableBarragem.setItems(observableList);

        String name = inputName.getText();
        String city = inputCity.getText();
        if(name != null && city != null){
            Barragem b = new Barragem(name,city);
            barragem.add(b);
            clear();
        }
        else
            System.out.println("erro");

    }

    private void clear() {
        inputCity.setText("");
        inputName.setText("");
    }

    public void teste(ActionEvent actionEvent) throws Exception {
        Barragem remove = (Barragem) tableBarragem.getSelectionModel().getSelectedItem();
        if(remove != null){
            WindowBarragem view = new WindowBarragem();
            view.start(remove);
        }
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
