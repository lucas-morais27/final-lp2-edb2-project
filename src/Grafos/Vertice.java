package grafos;

import java.util.LinkedList;

public class Vertice<TIPO> {
    private LinkedList<Aresta<TIPO>> arestasEntrada;
    private LinkedList<Aresta<TIPO>> arestasSaida;
    private TIPO dado;

    public Vertice(TIPO valor){
        this.dado = valor;
        this.arestasEntrada = new LinkedList<>();
        this.arestasSaida = new LinkedList<>();
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
