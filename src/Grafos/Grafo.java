package Grafos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import Trees.Arvore;
import Trees.ConjuntoDisjunto;

public class Grafo<TIPO> {
    private ArrayList<Aresta<TIPO>> arestas;
    private ArrayList<Vertice<TIPO>> vertices;
    private ConjuntoDisjunto<TIPO> floresta;
    private ArrayList<Aresta<TIPO>> elos;
    private int numCasas;
    private int numMaxArestas;

    public Grafo() throws IOException {
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.floresta = new ConjuntoDisjunto<>();
        this.elos = new ArrayList<>();
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

    public void setNumCasas(int numero) {
        this.numCasas = numero;
    }

    public int getNumCasas() {
        return this.numCasas;
    }

    public void setNumMaxArestas(int numero) {
        this.numMaxArestas = numero;
    }

    public void clearEntradaeSaida(ArrayList<Vertice<TIPO>> vertices){
        for(Vertice<TIPO> dado : vertices){
            dado.clearArestas();
        }
    }

    // Algoritmo de Kruskal para arvore geradora minima
    public void arvoreGeradoraMinima(int numMaxArestas) throws IOException{
        ArrayList<Aresta<TIPO>> arestasValidas = new ArrayList<>();
        int custoTotal = 0;

        floresta.criaConjunto(this.vertices);
        this.arestas.sort(null);
        //arestas.forEach((dado) -> System.out.println(dado.getCusto() + " " + dado.getInicio().getDado() + "-->" + dado.getFim().getDado()));

        for(Aresta<TIPO> dado : this.arestas){
            if(floresta.uneElementos(dado.getInicio(), dado.getFim(), numMaxArestas)){
                custoTotal += dado.getCusto();
                arestasValidas.add(dado);
            }
        }
        //elos.forEach((dado) -> System.out.println(dado.getInicio().getDado() + "-->" + dado.getFim().getDado()));
        escreveSolucao(arestasValidas, custoTotal);
        floresta.imprimeConjunto(this.vertices, floresta);
        floresta.clearConjunto();
    }

    public void allArvoresGeradoras(int numMaxArestas){
        floresta.criaConjunto(this.vertices);
        LinkedList<Aresta<TIPO>> arestasValidas = new LinkedList<>();
        // arvore geradora inicial
        clearEntradaeSaida(this.vertices);
        for(Aresta<TIPO> dado : this.arestas){
            if(floresta.uneElementos(dado.getInicio(), dado.getFim(), numMaxArestas)){
                dado.getInicio().addArestaSaida(dado);
                dado.getFim().addArestaEntrada(dado);
                arestasValidas.add(dado);
            }else{
                elos.add(dado);
            }
        }

        Aresta<TIPO> atual;
        // Todas as arvores a partir dos elos
        int count1=0;
        int count2=0;
        ArrayList<Aresta<TIPO>> potencialCiclo = new ArrayList<>();
        
        while(elos.size() > 0){
            atual = elos.remove(0);
            atual.getInicio().addArestaSaida(atual);
            atual.getFim().addArestaEntrada(atual);
            arestasValidas.add(atual);
            for(Aresta<TIPO> dado : arestasValidas){
                if(dado.getInicio() == atual.getFim()){
                    count1++;
                }
                if(dado.getFim() == atual.getFim()){
                    count2++;
                }
            }
        }
        arestasValidas.forEach((dado) -> System.out.println(dado.getInicio().getDado() + "-->" + dado.getFim().getDado()));
        arestasValidas.forEach((dado) -> System.out.println(dado.getInicio().getQntArestasEntrada() + " " + dado.getInicio().getQntArestasSaida() + "-->" + dado.getFim().getQntArestasEntrada() + " " + dado.getFim().getQntArestasSaida()));
        floresta.clearConjunto();
    }

    public void escreveSolucao(ArrayList<Aresta<TIPO>> arestas, int custo) throws IOException {
        FileWriter fileWriter = new FileWriter("src/arquivos/solucaoMenorCusto.txt");
        BufferedWriter buffWrite = new BufferedWriter(fileWriter);

        for(var dado : arestas){
            buffWrite.write(dado.getInicio().getDado() + " --> " + dado.getFim().getDado());
            buffWrite.newLine();
        }

        buffWrite.write(Integer.toString(custo));

        try {
            buffWrite.close();
        } catch (Exception e) {
            System.out.println("Arquivo de solução não pôde ser fechado.");
        }
        
	}
}
