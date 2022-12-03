package combinacoes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
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
    private int custoArvoreMinima = 0;
    private int numMaxArestas;
    private Graph graph;

    public ArvoreGeradora(ArrayList<Aresta<TIPO>> arestas, ArrayList<Vertice<TIPO>> vertices, int numMaxArestas) throws IOException {
        this.arestasTemporarias = new LinkedList<Aresta<TIPO>>();
        this.arvoresValidas = new HashMap<>();
        this.numMaxArestas = numMaxArestas;
        this.arestas = arestas;
        this.vertices = vertices;
    }
       
    private void combinacoes(ArrayList<Aresta<TIPO>> n, int left, int k) throws NumberFormatException, IOException {
        if (k == 0) {
            String[] resultado = validaArvore(arestasTemporarias).split(" ");
            if (resultado[0].equals("true")) {
                if (custoArvoreMinima == 0) {
                    this.custoArvoreMinima = Integer.parseInt(resultado[1]);
                } else {
                    if (this.custoArvoreMinima > Integer.parseInt(resultado[1])) {
                        this.custoArvoreMinima = Integer.parseInt(resultado[1]);
                    }
                }
            }
            return;
        }
  
        for (int ii = left; ii < n.size(); ++ii) {
            arestasTemporarias.add(n.get(ii));
            combinacoes(n, ii+1, k-1);
            arestasTemporarias.remove(arestasTemporarias.get(arestasTemporarias.size() - 1));
        }
    }

    @SuppressWarnings("unchecked")
    public void geraArvores() throws NumberFormatException, IOException {
        combinacoes(arestas, 0, vertices.size()-1);   
        var arvoresOrdenadas = ordenaArvores(arvoresValidas);
        var entry = arvoresOrdenadas.firstEntry();

        /* IMPRIME TODOS
        for (var item : arvoresOrdenadas.entrySet()) {
            System.out.println("key: " + item.getKey() + " value: " + item.getValue());
        }
        IMPRIME O PRIMERO
        System.out.println("PRIMEIRO: key: " + entry.getKey() + " value: " + entry.getValue());
        */

        escreveSolucao((LinkedList<Aresta<TIPO>>)entry.getKey(), entry.getValue() , "src/arquivos/solucaoMenorCusto.txt");
        this.arvoreGeradoraMinima = new LinkedList<>((LinkedList<Aresta<TIPO>>)entry.getKey());
    }
    
    private String validaArvore(LinkedList<Aresta<TIPO>> arvoreEmPotencial) throws IOException{
        ConjuntoDisjunto<TIPO> floresta = new ConjuntoDisjunto<>();
        LinkedList<Aresta<TIPO>> arestasValidas = new LinkedList<>();
        int custoTotal = 0;

        floresta.criaConjunto(vertices);
        for (Aresta<TIPO> dado : arvoreEmPotencial) {
            if (floresta.uneElementos(dado.getInicio(), dado.getFim(), this.numMaxArestas)) {
                custoTotal += dado.getCusto();
                arestasValidas.add(dado);
            }
        }
        if (arestasValidas.size() < vertices.size()-1) {
            return "false" + " " + 0;
        } else {
            LinkedList<?> arvoreValida = (LinkedList<?>)arestasValidas.clone();
            arvoresValidas.put(arvoreValida, custoTotal);
            return "true" + " " + custoTotal;
        }   
    }

    private <K, V extends Comparable<V>> TreeMap<K, V> ordenaArvores(final HashMap<K, V> map) {
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
    public void mostraInterfaceGrafica() {        
        System.setProperty("org.graphstream.ui", "swing");

		graph = new SingleGraph("Grafo");

        String styleSheet = "node {" 
        + "size: 30px, 30px;" 
        + "fill-mode: image-scaled; fill-image: url('src/arquivos/609803.png');" 
        + "text-alignment: under; text-color: white; text-style: bold; text-background-mode: rounded-box; text-background-color: #222C; text-padding: 1px; text-offset: 0px, 2px;" 
        + "}" + "edge {" + "text-alignment: under; text-offset: 4px, 3px; text-color: #444; text-style:bold; text-size: 13%;" + "}";
        graph.setAttribute("ui.stylesheet", styleSheet);

        for (Vertice<TIPO> vertice : vertices) {
            graph.addNode((String)vertice.getDado()).setAttribute("ui.label", (String)vertice.getDado());
        }

        for (Aresta<TIPO> aresta : arvoreGeradoraMinima) {
            graph.addEdge((String)aresta.getInicio().getDado() + aresta.getFim().getDado(), (String)aresta.getInicio().getDado(), (String)aresta.getFim().getDado()).setAttribute("ui.label", aresta.getCusto());;
        }
        this.graph.display();
    }
}
