package model;

public class SistemaSismoAlarme extends Dispositivos{
    private double abalo;
    private final double seguranca = 6.0;
    private String status;

    public SistemaSismoAlarme(int id,double abalo, String status) {
        super(id);
        this.abalo = abalo;
        this.status = status;
    }

    public SistemaSismoAlarme(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
