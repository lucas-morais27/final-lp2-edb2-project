package Grafos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

    // Algoritmo de Kruskal para arvore geradora minima
    /*public LinkedList<Aresta<TIPO>> arvoreGeradoraMinima(int numMaxArestas) throws IOException{
        LinkedList<Aresta<TIPO>> arestasValidas = new LinkedList<>();
        int custoTotal = 0;
        //ArrayList<Aresta<TIPO>> temporario = new ArrayList<>(Arrays.asList(arestas.get(0), arestas.get(1), arestas.get(2)));
        //Permutacao<TIPO> perm = new Permutacao<>();
        //perm.permuta(temporario);

        Combinacoes<TIPO> comb = new Combinacoes<>();
        allArvoresGeradoras = comb.makeCombi(this.arestas, this.vertices.size()-1);


        floresta.criaConjunto(this.vertices);
        this.arestas.sort(null);
        //arestas.forEach((dado) -> System.out.println(dado.getCusto() + " " + dado.getInicio().getDado() + "-->" + dado.getFim().getDado()));
        clearEntradaeSaida(this.vertices);
        for(Aresta<TIPO> dado : this.arestas){
            Vertice<TIPO> x = floresta.encontraElemento(dado.getInicio());
            Vertice<TIPO> y = floresta.encontraElemento(dado.getFim());
            if(!x.getDado().equals(y.getDado())){
                if(floresta.uneElementos(dado.getInicio(), dado.getFim(), numMaxArestas)){
                    custoTotal += dado.getCusto();
                    arestasValidas.add(dado);
                }
            }
            if(floresta.uneElementos(dado.getInicio(), dado.getFim(), numMaxArestas)){
                if(floresta.getRank(dado.getInicio()) >= floresta.getRank(dado.getFim())){
                    dado.getInicio().addArestaEntrada(dado);
                    dado.getFim().addArestaSaida(dado);
                }else if(floresta.getRank(dado.getInicio()) < floresta.getRank(dado.getFim())){
                    dado.getInicio().addArestaSaida(dado);
                    dado.getFim().addArestaEntrada(dado);
                }
                custoTotal += dado.getCusto();
                arestasValidas.add(dado);
            }else{
                this.elos.add(dado);
            }
        }
        //elos.forEach((dado) -> System.out.println(dado.getInicio().getDado() + "-->" + dado.getFim().getDado()));
        escreveSolucao(arestasValidas, custoTotal, "src/arquivos/solucaoMenorCusto.txt");
        //floresta.imprimeConjunto(this.vertices, floresta);
        floresta.clearConjunto();
        return arestasValidas;
    }*/

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

    public void allArvoresGeradoras()throws IOException{
        arvoresGeradoras = new ArvoresGeradoras<>(this, this.numMaxArestas);
        arvoresGeradoras.geraArvores();
        //arvoresGeradoras.teste();
    }

    public void escreveSolucao(ArrayList<Aresta<TIPO>> arestas, int custo, String arquivo) throws IOException {
        FileWriter fileWriter = new FileWriter(arquivo);
        BufferedWriter buffWrite = new BufferedWriter(fileWriter);

        for(var dado : arestas){
            buffWrite.write(dado.getInicio().getDado() + " --> " + dado.getFim().getDado());
            buffWrite.newLine();
        }

        buffWrite.write(Integer.toString(custo));

        try{
            buffWrite.close();
        } catch (Exception e){
            System.out.println("Arquivo de solução não pôde ser fechado.");
        }
	}
}
