package model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;

public class SistemaSismo extends Dispositivos{
    private boolean varBinaria;
    private String status;

    public SistemaSismo(int id, boolean bin, String status) {
        super(id);
        this.varBinaria = bin;
        this.status = status;

    }

    public boolean isVarBinaria() {
        return varBinaria;
    }

    public void setVarBinaria(boolean varBinaria) {
        this.varBinaria = varBinaria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SistemaSismo{" +
                "varBinaria=" + varBinaria + " id: " + getId();
    }

}




