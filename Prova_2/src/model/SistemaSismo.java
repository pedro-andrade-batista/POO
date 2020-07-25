package model;

public class SistemaSismo extends Dispositivos{
    private boolean varBinaria;

    public SistemaSismo(int id, boolean bin) {
        super(id);
        this.varBinaria = bin;
    }


    public boolean isVarBinaria() {
        return varBinaria;
    }

    public void setVarBinaria(boolean varBinaria) {
        this.varBinaria = varBinaria;
    }
}
