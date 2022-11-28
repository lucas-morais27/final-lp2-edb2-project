package Grafos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import combinacoes.ArvoresGeradoras;
import conjuntos.ConjuntoDisjunto;


public class Grafo<TIPO> {
    private ArrayList<Aresta<TIPO>> arestas;
    private ArrayList<Vertice<TIPO>> vertices;
    private ConjuntoDisjunto<TIPO> floresta;
    private ArvoresGeradoras<TIPO> arvoresGeradoras;
    private int numVertices;
    private int numMaxArestas;

    public Grafo() throws IOException {
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.floresta = new ConjuntoDisjunto<>();
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

    public void clearEntradaeSaida(ArrayList<Vertice<TIPO>> vertices){
        for(Vertice<TIPO> dado : vertices){
            dado.clearArestas();
        }
    }

    public void setNumMaxArestas(int numMaxArestas) {
        this.numMaxArestas = numMaxArestas;
    }
    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public String validaArvore(ArrayList<Aresta<TIPO>> arvoreEmPotencial, int numMaxArestas) throws IOException{
        ArrayList<Aresta<TIPO>> arestasValidas = new ArrayList<>();
        int custoTotal = 0;

        floresta.criaConjunto(this.vertices);
        //clearEntradaeSaida(this.vertices);
        for(Aresta<TIPO> dado : arvoreEmPotencial){
            if(floresta.uneElementos(dado.getInicio(), dado.getFim(), numMaxArestas)){
                //dado.getInicio().addArestaSaida(dado);
                //dado.getFim().addArestaEntrada(dado);
                custoTotal += dado.getCusto();
                arestasValidas.add(dado);
            }
        }

        if(arestasValidas.size() < this.vertices.size()-1){
            floresta.clearConjunto();
            return "false" + " " + 0;
        }else{
            floresta.clearConjunto();
            return "true" + " " + custoTotal;
        }   
    }

    public void allArvoresGeradoras() throws IOException {
        arvoresGeradoras = new ArvoresGeradoras<>(this, this.numMaxArestas);
        arvoresGeradoras.geraArvores();
    }

    public void escreveSolucao(LinkedList<String> lista)  throws IOException {
        FileWriter fileWriter = new FileWriter("../src/arquivos/solucaoMenorCusto.txt");
        BufferedWriter buffWrite = new BufferedWriter(fileWriter);
        
        for(var dado : lista){
            buffWrite.write(dado);
            buffWrite.newLine();
        }

        try{
            buffWrite.close();
        } catch (Exception e){
            System.out.println("Arquivo de solução não pôde ser fechado.");
        }
    }

    public void escreveSolucao(ArrayList<Aresta<TIPO>> arestas, int custo, String arquivo) throws IOException {
        FileWriter fileWriter = new FileWriter(arquivo, true);
        BufferedWriter buffWrite = new BufferedWriter(fileWriter);

        for(var dado : arestas){
            buffWrite.write(dado.getInicio().getDado() + " --> " + dado.getFim().getDado());
            buffWrite.newLine();
        }

        buffWrite.write(Integer.toString(custo));
        buffWrite.newLine();

        try{
            buffWrite.close();
        } catch (Exception e){
            System.out.println("Arquivo de solução não pôde ser fechado.");
        }
	}
}
