package Grafos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Trees.ConjuntoDisjunto;
import combinacoes.ArvoresGeradoras;


public class Grafo<TIPO> {
    private ArrayList<Aresta<TIPO>> arestas;
    private ArrayList<Vertice<TIPO>> vertices;
    private ConjuntoDisjunto<TIPO> floresta;
    //private ArrayList<Aresta<TIPO>> elos;
    //private ArrayList<ArrayList<Aresta<TIPO>>> allArvoresGeradoras;
    private ArvoresGeradoras<TIPO> arvoresGeradoras;
    private int numVertices;
    private int numMaxArestas;

    public Grafo() throws IOException {
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.floresta = new ConjuntoDisjunto<>();
        //this.allArvoresGeradoras = new ArrayList<ArrayList<Aresta<TIPO>>>();;
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

    public String geradorArvores(ArrayList<Aresta<TIPO>> arvoreEmPotencial, int numMaxArestas) throws IOException{
        //ConjuntoDisjunto<TIPO> floresta = new ConjuntoDisjunto<>();
        ArrayList<Aresta<TIPO>> arestasValidas = new ArrayList<>();
        int custoTotal = 0;

        floresta.criaConjunto(this.vertices);
        //System.out.println(arvoreEmPotencial.size());
        //arvoreEmPotencial.forEach((dado) -> System.out.println(dado.getCusto() + " " + dado.getInicio().getDado() + "-->" + dado.getFim().getDado()));
        //System.out.println();
        //clearEntradaeSaida(this.vertices);
        for(Aresta<TIPO> dado : arvoreEmPotencial){
            if(floresta.uneElementos(dado.getInicio(), dado.getFim(), numMaxArestas)){
                //dado.getInicio().addArestaSaida(dado);
                //dado.getFim().addArestaEntrada(dado);
                custoTotal += dado.getCusto();
                arestasValidas.add(dado);
            }
        }
        //arestasValidas.forEach((dado) -> System.out.println(dado.getCusto() + " " + dado.getInicio().getDado() + "-->" + dado.getFim().getDado()));
        //System.out.println();
        if(arestasValidas.size() < this.vertices.size()-1){
            floresta.clearConjunto();
            return "false" + " " + 0;
        }else{
            /*if(!floresta.validaConjunto(this.vertices)){
                floresta.clearConjunto();
                return "false" + " " + 0;
            }*/
            floresta.clearConjunto();
            return "true" + " " + custoTotal;
        }
        //elos.forEach((dado) -> System.out.println(dado.getInicio().getDado() + "-->" + dado.getFim().getDado()));
        //escreveSolucao(arestasValidas, custoTotal);
        /*if(floresta.validaConjunto(this.vertices, floresta)){
            floresta.clearConjunto();
            return "true" + " " + custoTotal;
        }else{
            floresta.clearConjunto();
            return "false";
        }*/        
    }

    public void allArvoresGeradoras()throws IOException{
        //floresta.criaConjunto(this.vertices);
        //LinkedList<Aresta<TIPO>> arestasValidas = arvoreGeradoraMinima(numMaxArestas);
        arvoresGeradoras = new ArvoresGeradoras<>(this, this.numMaxArestas);
        arvoresGeradoras.geraArvores();
        //this.allArvoresGeradoras = .makeCombi(this.arestas, this.vertices.size()-1);
        //allArvoresGeradoras = arvoresGeradoras.getResultadosPossiveis();
        //System.out.println(allArvoresGeradoras.size());
        //int count1=1;

        /*for(ArrayList<Aresta<TIPO>> sequencia : allArvoresGeradoras){
            sequencia.forEach((dado) -> System.out.println(dado.getInicio().getQntArestasEntrada() + " " + dado.getInicio().getQntArestasSaida() + "-->" + dado.getFim().getQntArestasEntrada() + " " + dado.getFim().getQntArestasSaida()));
            String[] resultado = geradorArvores(sequencia, numMaxArestas).split(" ");
            if(resultado[0].equals("true")){
                escreveSolucao(sequencia, Integer.parseInt(resultado[1]), "src/arquivos/solucao" + count1 + ".txt");
                count1++;
            }
        }*/
        //arestasValidas.forEach((dado) -> System.out.println(dado.getInicio().getQntArestasEntrada() + " " + dado.getInicio().getQntArestasSaida() + "-->" + dado.getFim().getQntArestasEntrada() + " " + dado.getFim().getQntArestasSaida()));
        //Aresta<TIPO> atual;
        // Todas as arvores a partir dos elos
        //int count1=1;
        //elos.forEach((dado) -> System.out.println(dado.getInicio().getDado() + "-->" + dado.getFim().getDado()));
        /*while(elos.size() > 0){
            atual = elos.remove(0);
            atual.getInicio().addArestaSaida(atual);
            atual.getFim().addArestaEntrada(atual);
            arestasValidas.addLast(atual);
            Aresta<TIPO> temp1 = arestasValidas.get(0);
            while(!temp1.equals(atual)){
                temp1 = arestasValidas.removeFirst();
                String[] resultado = geradorArvores(arestasValidas, numMaxArestas).split(" ");
                if(resultado[0].equals("true")){
                    escreveSolucao(arestasValidas, Integer.parseInt(resultado[1]), "src/arquivos/solucao" + count1 + ".txt");
                    count1++;
                }
                arestasValidas.addLast(temp1);
            }
        }*/
        //elos.forEach((dado) -> System.out.println(dado.getInicio().getDado() + "-->" + dado.getFim().getDado()));
        //arestasValidas.forEach((dado) -> System.out.println(dado.getInicio().getQntArestasEntrada() + " " + dado.getInicio().getQntArestasSaida() + "-->" + dado.getFim().getQntArestasEntrada() + " " + dado.getFim().getQntArestasSaida()));
        //floresta.clearConjunto();
    }

    public void allArvoresGeradorasPorCusto(int numMaxArestas){
    }

    public void escreveSolucao(ArrayList<Aresta<TIPO>> arestas, int custo, String arquivo) throws IOException {
        FileWriter fileWriter = new FileWriter(arquivo);
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
