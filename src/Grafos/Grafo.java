package Grafos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Trees.Arvore;
import Trees.ConjuntoDisjunto;

public class Grafo<TIPO> {
    private ArrayList<Aresta<TIPO>> arestas;
    private ArrayList<Vertice<TIPO>> vertices;
    private ConjuntoDisjunto<TIPO> floresta;
    private int numCasas;
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

    public void setNumCasas(int numero) {
        this.numCasas = numero;
    }

    public int getNumCasas() {
        return this.numCasas;
    }

    public void setNumMaxArestas(int numero) {
        this.numMaxArestas = numero;
    }

    // Algoritmo de Kruskal para arvore geradora minima
    public void arvoreGeradoraMinima() throws IOException{
        ArrayList<Aresta<TIPO>> allArestas = this.arestas;
        ArrayList<Aresta<TIPO>> arestasValidas = new ArrayList<>();
        Aresta<TIPO> atual;
        int custoMinimo = 0;

        floresta.criaConjunto(this.vertices);
        allArestas.sort(null);

        while(allArestas.size() > 0){
            atual = allArestas.remove(0);
            if(floresta.uneElementos(atual.getInicio(), atual.getFim(), numMaxArestas)){
                custoMinimo += atual.getCusto();
                arestasValidas.add(atual);
            }
        }

        escreveSolucao(arestasValidas, custoMinimo);
        floresta.imprimeConjunto(this.vertices, floresta);

        /*
        Criação e escrita no arquivo da solução de menor custo
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
                arestasAux.add(atual);
            }
        }
        for(Aresta<TIPO> dado : arestasAux){
            bw.write(dado.getInicio().getDado() + " --> " + dado.getFim().getDado());
            bw.newLine();
        }
        bw.write(Integer.toString(custoMinimo));
        bw.close();
        fw.close();
        floresta.imprimeConjunto(this.vertices, floresta);
        */
    }

    public void escreveSolucao(ArrayList<Aresta<TIPO>> arestas, int custo) throws IOException {
        FileWriter fileWriter = new FileWriter("../src/arquivos/solucaoMenorCusto.txt");
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
