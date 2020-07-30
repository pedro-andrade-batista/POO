package model;

import java.util.Observable;
import java.util.Observer;

public class Dispositivos {
    private int id;

    public Dispositivos(int id) {
        this.id = id;
    }
    public Dispositivos(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
