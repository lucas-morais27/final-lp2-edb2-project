package grafos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import interfaces.InterfaceGrafica;
import combinacoes.ArvoreGeradora;

public class Grafo<TIPO> implements InterfaceGrafica{
    private ArvoreGeradora<TIPO> arvoresGeradoras;
    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;
    private int numMaxArestas;
    private int numVertices;

    public Grafo() throws IOException {
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

    public void setNumMaxArestas(int numMaxArestas) {
        this.numMaxArestas = numMaxArestas;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int getNumMaxArestas() {
        return numMaxArestas;
    }

    public void allArvoresGeradoras() throws IOException {
        arvoresGeradoras = new ArvoreGeradora<>(this.arestas, this.vertices, this.numMaxArestas);
        arvoresGeradoras.geraArvores();
    }

    public void escreveSolucao(LinkedList<Aresta<TIPO>> arestas, int custo, String arquivo) throws IOException {
        FileWriter fileWriter = new FileWriter(arquivo);
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

    public void mostraArvoreGeradoraMinima(){
        arvoresGeradoras.mostraInterfaceGrafica();
    }

    @Override
    public void mostraInterfaceGrafica() {
        System.setProperty("org.graphstream.ui", "swing");
		
		Graph graph = new SingleGraph("Grafo");

        String styleSheet = "node {" 
        + "size: 30px, 30px;" 
        + "fill-mode: image-scaled; fill-image: url('src/arquivos/609803.png');" 
        + "text-alignment: under; text-color: white; text-style: bold; text-background-mode: rounded-box; text-background-color: #222C; text-padding: 1px; text-offset: 0px, 2px;" 
        + "}" + "edge{" + "text-alignment: under; text-offset: 4px, 3px; text-color: #444; text-style:bold; text-size: 13%;" + "}";
        graph.setAttribute("ui.stylesheet", styleSheet);

        for (Vertice<TIPO> vertice : vertices){
            graph.addNode((String)vertice.getDado()).setAttribute("ui.label", (String)vertice.getDado());;
        }
        for (Aresta<TIPO> aresta : arestas) {
            graph.addEdge((String)aresta.getInicio().getDado() + aresta.getFim().getDado(), (String)aresta.getInicio().getDado(), (String)aresta.getFim().getDado()).setAttribute("ui.label", aresta.getCusto());
        }

		graph.display();
    }
}
