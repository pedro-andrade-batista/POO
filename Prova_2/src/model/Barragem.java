package model;

import java.util.*;


public class Barragem{
    private String nome,cidade;
    private int identificadorAbalos;
    private List<Dispositivos> dispositivos = new ArrayList<>();


    public Barragem(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }
    public Barragem(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getIdentificadorAbalos() {
        return identificadorAbalos;
    }

    public void setIdentificadorAbalos(int identificadorAbalos) {
        this.identificadorAbalos = identificadorAbalos;
    }

    public void addDispositivos(Dispositivos d){
        dispositivos.add(d);
    }

    public void removeDispositivos(Dispositivos d){
        dispositivos.remove(d);
    }
    public List<Dispositivos> getDispositivos() {
        return dispositivos;
    }


}
