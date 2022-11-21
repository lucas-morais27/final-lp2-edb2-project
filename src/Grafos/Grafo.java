package Grafos;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Trees.Arvore;
import Trees.ConjuntoDisjunto;

public class Grafo<TIPO> {
    private ArrayList<Aresta<TIPO>> arestas;
    private ArrayList<Vertice<TIPO>> vertices;

    public Grafo(){
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
    }

    public void addVertice(TIPO dado){
        Vertice<TIPO> novo = new Vertice<>(dado);
        this.vertices.add(novo);
    }

    public void addAresta(int custo, TIPO dadoInicio, TIPO dadoFim){
        Vertice<TIPO> inicio = getVertice(dadoInicio);
        Vertice<TIPO> fim = getVertice(dadoFim);
        Aresta<TIPO> nova = new Aresta<>(custo, inicio, fim);
        inicio.addArestaSaida(nova);
        fim.addArestaEntrada(nova);
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

    public void arvoreGeradoraMinima(int numMaxArestas){
        ConjuntoDisjunto<TIPO> floresta = new ConjuntoDisjunto<>();
        ArrayList<Aresta<TIPO>> allArestas = this.arestas;
        ArrayList<Aresta<TIPO>> arestasValidas = new ArrayList<>();
        Aresta<TIPO> atual;
        int custoMinimo = 0;

        floresta.criaConjunto(this.vertices);
        allArestas.sort(null);
        //Arvore<TIPO> arvoreMinima = new Arvore<>();

        while(allArestas.size() > 0){
            atual = allArestas.remove(0);
            if(floresta.uneElementos(atual.getInicio(), atual.getFim(), numMaxArestas)){
                custoMinimo += atual.getCusto();
                arestasValidas.add(atual);
            }
        }
        for(Aresta<TIPO> dado : arestasValidas){
            System.out.println(dado.getInicio().getDado() + " --> " + dado.getFim().getDado());
        }
        System.out.println(custoMinimo);
        floresta.imprimeConjunto(this.vertices, floresta);
    }
}
