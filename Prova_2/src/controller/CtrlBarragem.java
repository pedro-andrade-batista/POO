package controller;

import javafx.event.ActionEvent;
import loaders.WindowAC;
import loaders.WindowSS;
import loaders.WindowSSA;

public class CtrlBarragem {
    public void showSS(ActionEvent actionEvent) throws Exception {
        WindowSS view = new WindowSS();
        view.start();
    }

    public void showSSA(ActionEvent actionEvent) throws Exception {
        WindowSSA view = new WindowSSA();
        view.start();
    }

    public void showAC(ActionEvent actionEvent) throws Exception {
        WindowAC view = new WindowAC();
        view.start();
    }
}
