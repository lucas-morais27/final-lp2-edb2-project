package combinacoes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeMap;

import grafos.Aresta;
import grafos.Grafo;
import grafos.Vertice;
import conjuntos.ConjuntoDisjunto;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class ArvoreGeradora<TIPO> extends Grafo<TIPO>{
    private HashMap<LinkedList<?>, Integer> arvoresValidas;
    private LinkedList<Aresta<TIPO>> arvoreGeradoraMinima;
    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;
    private LinkedList<Aresta<TIPO>> arestasTemporarias;
    private int numMaxArestas;
    private Graph graph;

    public ArvoreGeradora(ArrayList<Aresta<TIPO>> arestas, ArrayList<Vertice<TIPO>> vertices, int numMaxArestas) throws IOException {
        this.arestasTemporarias = new LinkedList<Aresta<TIPO>>();
        this.arvoresValidas = new HashMap<>();
        this.numMaxArestas = numMaxArestas;
        this.arestas = arestas;
        this.vertices = vertices;
    }
       
    private void combinacoes(ArrayList<Aresta<TIPO>> arestas, int contador, int qntElementos) throws IOException {
        if (qntElementos == 0) { // quando a combinação foi formada
            validaArvore(arestasTemporarias); // valida a combinação de arestas (verifica se é ou não uma arvore geradora do grafo)
            return; // retrocede as chamadas recursivas
        }
  
        for (int ii = contador; ii < arestas.size(); ++ii) {
            arestasTemporarias.add(arestas.get(ii)); // adiciona uma aresta à combinação
            combinacoes(arestas, ii+1, qntElementos-1); // chamada recursiva, adentra nos qntElementos loops para formar a combinação
            arestasTemporarias.removeLast(); // remove o último elemento
        }
    }

    @SuppressWarnings("unchecked")
    public void geraArvores() throws IOException {
        combinacoes(arestas, 0, vertices.size()-1);
        var arvoresOrdenadas = ordenaArvores(arvoresValidas); // ordena as arvores geradoras
        var entry = arvoresOrdenadas.firstEntry(); // pega a primeira, que é a de menor custo
        // armazena a solução de menor custo em um arquivo
        escreveSolucao((LinkedList<Aresta<TIPO>>)entry.getKey(), entry.getValue(), "src/arquivos/solucaoMenorCusto.txt");
        this.arvoreGeradoraMinima = new LinkedList<>((LinkedList<Aresta<TIPO>>)entry.getKey()); // armazena em uma estrutura de dados separada das demais
    }
    
    private void validaArvore(LinkedList<Aresta<TIPO>> arvoreEmPotencial) throws IOException{
        ConjuntoDisjunto<TIPO> floresta = new ConjuntoDisjunto<>();
        LinkedList<Aresta<TIPO>> arestasValidas = new LinkedList<>();
        int custoTotal = 0;

        floresta.criaConjunto(vertices); // faz com que cada vertice seja uma arvore (cria uma floresta)
        for (Aresta<TIPO> dado : arvoreEmPotencial) { // percorre o conjunto de arestas passado por parametro
            if (floresta.uneElementos(dado.getInicio(), dado.getFim(), this.numMaxArestas)) { // caso os elementos sejam de conjuntos/arvores distintos/as
                custoTotal += dado.getCusto(); // adiciona o custo da aresta ao custo da arvore
                arestasValidas.add(dado); // adiona a aresta como valida, ou seja, foi possivel unir os dois vertices
            }
        }
        if (arestasValidas.size() == vertices.size()-1) { // caso a arvore gerada possua n-1 arestas (uma das condições para ser uma arvore geradora de um grafo com n vertices)
            LinkedList<?> arvoreValida = (LinkedList<?>)arestasValidas.clone(); // clona a variavel
            arvoresValidas.put(arvoreValida, custoTotal); // adiciona a arvore geradora e seu respectivo custo em um Map
        }   
    }

    private <K, V extends Comparable<V>> TreeMap<K, V> ordenaArvores(final HashMap<K, V> map) { // ordena as arvores geradoras
        Comparator<K> valorComparador =  new Comparator<K>() {
            public int compare(K valor1, K valor2) {
                int compare = map.get(valor1).compareTo(map.get(valor2));
                if (compare == 0) {
                    return 1;
                } else {
                    return compare;
                }
            }
        };
        TreeMap<K, V> arvoresOrdenadas = new TreeMap<K, V>(valorComparador);
        arvoresOrdenadas.putAll(map);
        return arvoresOrdenadas;
    }

    @Override
    public void mostraInterfaceGrafica(){        
        System.setProperty("org.graphstream.ui", "swing"); // cria a janela da interface gráfica

		graph = new SingleGraph("Grafo"); // cria o grafo

        Random ale = new Random();
        // estilização do grafo (CSS)
        String styleSheet = "node {" 
        + "size: 35px, 35px;" 
        + "fill-mode: image-scaled; fill-image: url('src/arquivos/609803.png');"
        + "text-alignment: under; text-color: white; text-style: bold; text-background-mode: rounded-box; text-background-color: #222C; text-padding: 1px; text-offset: 0px, 2px;" 
        + "}" + "node#" + (String)vertices.get(ale.nextInt(1, vertices.size())).getDado() + "{ fill-image: url('src/arquivos/casaRoteador.png'); }" 
        + "edge {" + "text-alignment: under; text-offset: 4px, 3px; text-color: #444; text-style:bold; text-size: 13%;" + "fill-color: black; shadow-mode: plain; shadow-width: 2px; shadow-color: #1E90FF; shadow-offset: 0px;" + "}";
        graph.setAttribute("ui.stylesheet", styleSheet); // adiciona o atributo de estilização ao grafo

        for (Vertice<TIPO> vertice : vertices) { // adiciona os vertices do grafo + identificadores
            graph.addNode((String)vertice.getDado()).setAttribute("ui.label", (String)vertice.getDado());
        }

        for (Aresta<TIPO> aresta : arvoreGeradoraMinima) { // adiciona as arestas da arvore geradora minima + custo
            graph.addEdge((String)aresta.getInicio().getDado() + aresta.getFim().getDado(), (String)aresta.getInicio().getDado(), (String)aresta.getFim().getDado()).setAttribute("ui.label", aresta.getCusto());;
        }

        this.graph.display(); // gera a interface na tela
    }
}
