package Grafos;

import java.util.ArrayList;

public class Vertice<TIPO> {
    private TIPO dado;
    private ArrayList<Aresta<TIPO>> arestasEntrada;
    private ArrayList<Aresta<TIPO>> arestasSaida;

    public Vertice(TIPO valor){
        this.dado = valor;
        this.arestasEntrada = new ArrayList<>();
        this.arestasSaida = new ArrayList<>();
    }

    public TIPO getDado(){
        return this.dado;
    }

    public void setDado(TIPO dado){
        this.dado = dado;
    }

    public void addArestaEntrada(Aresta<TIPO> aresta){
        this.arestasEntrada.add(aresta);
    }

    public void addArestaSaida(Aresta<TIPO> aresta){
        this.arestasSaida.add(aresta);
    }

    public int getQntArestasEntrada() {
        return arestasEntrada.size();
    }
    
    public int getQntArestasSaida() {
        return arestasSaida.size();
    }
}
