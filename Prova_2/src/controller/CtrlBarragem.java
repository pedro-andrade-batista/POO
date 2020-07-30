package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import loaders.WindowAC;
import loaders.WindowSS;
import loaders.WindowSSA;
import model.*;

import java.util.Observable;
import java.util.Observer;


public class CtrlBarragem {
    @FXML private Label lbSSquant;
    @FXML private Label lbSSAquant;
    @FXML private Label lbACquant;
    @FXML private Label lbNumAlertaquant;
    @FXML private Label lbEstadoAlerta;
    @FXML private Label lbNomeBarragem;
    @FXML private Label lbNomeCidade;
    @FXML private Button btnfechar;

    private Barragem aux;



    private void showDadosAtualizados(Barragem aux) {
        System.out.println("aux = " + aux.getDispositivos().size());
    }

    public void showSS(ActionEvent actionEvent) throws Exception {
        WindowSS view = new WindowSS();
        view.start(this.aux);
    }

    public void showSSA(ActionEvent actionEvent) throws Exception {
        WindowSSA view = new WindowSSA();
        view.start(this.aux);
    }

    public void showAC(ActionEvent actionEvent) throws Exception {
        WindowAC view = new WindowAC();
        view.start(this.aux);
    }

    public void show(Barragem b){
        lbNomeBarragem.setText(lbNomeBarragem.getText() + ": " + b.getNome());
        lbNomeCidade.setText(lbNomeCidade.getText() + ": " + b.getCidade());
        int contadorSSA = 0, contadorSS = 0, contadorAC = 0;
        for (Dispositivos d : b.getDispositivos()) {
            System.out.println("d.getId() = " + d.getId());
            if(d instanceof SistemaSismo)
                contadorSS++;
            else if(d instanceof SistemaSismoAlarme)
                contadorSSA++;
            else
                contadorAC++;
        }
        lbACquant.setText(lbACquant.getText() + ": " + contadorAC);
        lbSSAquant.setText(lbSSAquant.getText() + ": " + contadorSSA);
        lbSSquant.setText(lbSSquant.getText() + ": " + contadorSS);
        this.aux = b;
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) btnfechar.getScene().getWindow();
        stage.close();
    }

}
