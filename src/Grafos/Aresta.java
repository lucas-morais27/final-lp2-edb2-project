package grafos;

public class Aresta<TIPO> {
    private Vertice<TIPO> inicio;
    private Vertice<TIPO> fim;
    private int custo;

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
}
