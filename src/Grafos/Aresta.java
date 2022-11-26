package Grafos;

public class Aresta<TIPO> implements Comparable<Aresta<TIPO>>{
    private int custo;
    private Vertice<TIPO> inicio;
    private Vertice<TIPO> fim;

    public Aresta(int custo, Vertice<TIPO> inicio, Vertice<TIPO> fim){
        this.custo = custo;
        this.inicio = inicio;
        this.fim = fim;
    }

    public int getCusto() {
        return custo;
    }

    public Vertice<TIPO> getInicio() {
        return inicio;
    }

    public Vertice<TIPO> getFim() {
        return fim;
    }

    @Override
    public int compareTo(Aresta<TIPO> o) {
        return this.custo - o.getCusto();
    }

    public boolean containsVertice(Vertice<TIPO> vertice){
        if(this.inicio.equals(vertice) || this.fim.equals(vertice)){
            return true;
        }else{
            return false;
        }
    }
}
