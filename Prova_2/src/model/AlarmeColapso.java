package model;

public class AlarmeColapso extends Dispositivos{
    private String status;

    public AlarmeColapso(int id, String status) {
        super(id);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
