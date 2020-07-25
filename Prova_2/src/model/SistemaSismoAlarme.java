package model;

public class SistemaSismoAlarme extends Dispositivos{
    private double abalo;
    private final double seguranca = 30;

    public SistemaSismoAlarme(int id,double abalo) {
        super(id);
        this.abalo = abalo;
    }


    public double getAbalo() {
        return abalo;
    }

    public void setAbalo(double abalo) {
        this.abalo = abalo;
    }

    public double getSeguranca() {
        return seguranca;
    }
}
