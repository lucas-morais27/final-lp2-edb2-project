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

    protected void addArestaEntrada(Aresta<TIPO> aresta){
        this.arestasEntrada.add(aresta);
    }

    protected void addArestaSaida(Aresta<TIPO> aresta){
        this.arestasSaida.add(aresta);
    }
}
