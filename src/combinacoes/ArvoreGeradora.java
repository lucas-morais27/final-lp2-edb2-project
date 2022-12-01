package combinacoes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import grafos.Aresta;
import grafos.Grafo;
import grafos.Vertice;
import conjuntos.ConjuntoDisjunto;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class ArvoreGeradora<TIPO> extends Grafo<TIPO> {
    private ArrayList<Aresta<TIPO>> arestas;
    private ArrayList<Vertice<TIPO>> vertices;
    private HashMap<ArrayList<?>, Integer> arvoresValidas = new HashMap<>();
    private Graph graph;
    private ArrayList<Aresta<TIPO>> tmp;
    private int numMaxArestas;
    private ArrayList<Aresta<TIPO>> arvoreGeradoraMinima;
    private int custoArvoreMinima = 0;

    public ArvoreGeradora(ArrayList<Aresta<TIPO>> arestas, ArrayList<Vertice<TIPO>> vertices, int numMaxArestas) throws IOException {
        super();
        this.tmp = new ArrayList<Aresta<TIPO>>();
        this.arestas = arestas;
        this.vertices = vertices;
        this.numMaxArestas = numMaxArestas;
    }
       
    private void combinacoes(ArrayList<Aresta<TIPO>> n, int left, int k) throws NumberFormatException, IOException {
        if (k == 0){
            ArrayList<?> dado = (ArrayList<?>)tmp.clone();
            String[] resultado = validaArvore(tmp).split(" ");
            if(resultado[0].equals("true")){
                if(custoArvoreMinima == 0){
                    custoArvoreMinima = Integer.parseInt(resultado[1]);
                }else{
                    if(custoArvoreMinima > Integer.parseInt(resultado[1])){
                        custoArvoreMinima = Integer.parseInt(resultado[1]);
                    }
                }
            }
            return;
        }
  
        for (int ii = left; ii < n.size(); ++ii){
            tmp.add(n.get(ii));
            combinacoes(n, ii+1, k-1);
            tmp.remove(tmp.get(tmp.size() - 1));
        }
    }

    public void geraArvores() throws NumberFormatException, IOException {
        combinacoes(arestas, 0, vertices.size()-1);   
        for (Entry<ArrayList<?>, Integer> elemento : arvoresValidas.entrySet()) {
            if(elemento.getValue() == custoArvoreMinima){
                escreveSolucao((ArrayList<Aresta<TIPO>>)elemento.getKey(), custoArvoreMinima, "src/arquivos/solucaoMenorCusto.txt");
                this.arvoreGeradoraMinima = (ArrayList<Aresta<TIPO>>)elemento.getKey();
                break;
            }
        }
    }
    
    public String validaArvore(ArrayList<Aresta<TIPO>> arvoreEmPotencial) throws IOException{
        ConjuntoDisjunto<TIPO> floresta = new ConjuntoDisjunto<>();
        ArrayList<Aresta<TIPO>> arestasValidas = new ArrayList<>();
        int custoTotal = 0;

        floresta.criaConjunto(vertices);
        //clearEntradaeSaida(this.vertices);
        for(Aresta<TIPO> dado : arvoreEmPotencial){
            if(floresta.uneElementos(dado.getInicio(), dado.getFim(), this.numMaxArestas)){
                //dado.getInicio().addArestaSaida(dado);
                //dado.getFim().addArestaEntrada(dado);
                custoTotal += dado.getCusto();
                arestasValidas.add(dado);
            }
        }

        if(arestasValidas.size() < vertices.size()-1){
            //floresta.clearConjunto();
            return "false" + " " + 0;
        }else{
            //floresta.clearConjunto();
            ArrayList<?> arvoreValida = (ArrayList<?>)arestasValidas.clone();
            arvoresValidas.put(arvoreValida, custoTotal);
            return "true" + " " + custoTotal;
        }   
    }

    @Override
    public void mostraInterfaceGrafica() {
        System.setProperty("org.graphstream.ui", "swing");
		
		graph = new SingleGraph("Grafo");
        String styleSheet = "node {" 
        + "size: 30px, 30px;" 
        + "fill-mode: image-scaled; fill-image: url('src/arquivos/609803.png');" 
        + "text-alignment: under; text-color: white; text-style: bold; text-background-mode: rounded-box; text-background-color: #222C; text-padding: 1px; text-offset: 0px, 2px;" 
        + "}" + "edge{" + "text-alignment: under; text-offset: 4px, 3px; text-color: #444; text-style:bold; text-size: 13%;" + "}";
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
