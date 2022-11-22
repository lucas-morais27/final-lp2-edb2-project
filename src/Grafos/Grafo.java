package Grafos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Trees.Arvore;
import Trees.ConjuntoDisjunto;

public class Grafo<TIPO>{
    private ArrayList<Aresta<TIPO>> arestas;
    private ArrayList<Vertice<TIPO>> vertices;

    public Grafo(){
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
    }

    public ArrayList<Aresta<TIPO>> getArestas() {
        return this.arestas;
    }

    public ArrayList<Vertice<TIPO>> getVertices() {
        return this.vertices;
    }

    public void addVertice(TIPO dado){
        Vertice<TIPO> novo = new Vertice<>(dado);
        this.vertices.add(novo);
    }

    public void addAresta(int custo, TIPO dadoInicio, TIPO dadoFim){
        Vertice<TIPO> inicio = getVertice(dadoInicio);
        Vertice<TIPO> fim = getVertice(dadoFim);
        Aresta<TIPO> nova = new Aresta<>(custo, inicio, fim);
        //ERRO NESSAS LINHAS vvvvv
        //inicio.addArestaSaida(nova);
        //fim.addArestaEntrada(nova);
        arestas.add(nova);
    }

    public Vertice<TIPO> getVertice(TIPO dado){
        for(Vertice<TIPO> vertice : this.vertices){
            if(vertice.getDado().equals(dado)){
                return vertice;
            }
        }
        return null;
    }

    public Aresta<TIPO> getAresta(int indice){
        return arestas.get(indice);
    }

    public void arvoreGeradoraMinima(int numMaxArestas) throws IOException{
        // Algoritmo de Kruskal para arvore geradora minima
        ConjuntoDisjunto<TIPO> floresta = new ConjuntoDisjunto<>();
        ArrayList<Aresta<TIPO>> allArestas = this.arestas;
        ArrayList<Aresta<TIPO>> arestasValidas = new ArrayList<>();
        Aresta<TIPO> atual;

        floresta.criaConjunto(this.vertices);
        allArestas.sort(null);
        int custoMinimo = 0;

        // Criação e escrita no arquivo da solução de menor custo
        File solution = new File("src/arquivos/solucaoMenorCusto.txt");
        if(!solution.exists()){
            solution.createNewFile();
        }
        FileWriter fw = new FileWriter(solution);
        BufferedWriter bw = new BufferedWriter(fw);

        while(allArestas.size() > 0){
            atual = allArestas.remove(0);
            if(floresta.uneElementos(atual.getInicio(), atual.getFim(), numMaxArestas)){
                custoMinimo += atual.getCusto();
                arestasValidas.add(atual);
            }
        }
        for(Aresta<TIPO> dado : arestasValidas){
            bw.write(dado.getInicio().getDado() + " --> " + dado.getFim().getDado());
            bw.newLine();
        }
        bw.write(Integer.toString(custoMinimo));
        bw.close();
        fw.close();
        floresta.imprimeConjunto(this.vertices, floresta);
    }
}
