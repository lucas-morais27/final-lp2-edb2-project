package combinacoes;

import java.io.IOException;
import java.util.ArrayList;

import Grafos.Aresta;
import Grafos.Grafo;
import Trees.ConjuntoDisjunto;

public class ArvoresGeradoras<TIPO>{
    private ArrayList<ArrayList<Aresta<TIPO>>> resultadosPossiveis = new ArrayList<ArrayList<Aresta<TIPO>>>();
    private ArrayList<ArrayList<Aresta<TIPO>>> resultadosValidos = new ArrayList<ArrayList<Aresta<TIPO>>>();;
    private ArrayList<Aresta<TIPO>> tmp = new ArrayList<Aresta<TIPO>>();
    private ArrayList<Aresta<TIPO>> elementos;
    private Grafo<TIPO> grafo;
    private int count1=1;
    private int numMaxArestas;

    public ArvoresGeradoras(Grafo<TIPO> grafo, int numMaxArestas) throws IOException {
        resultadosPossiveis = new ArrayList<ArrayList<Aresta<TIPO>>>();
        resultadosValidos = new ArrayList<ArrayList<Aresta<TIPO>>>();
        tmp = new ArrayList<Aresta<TIPO>>();
        this.grafo = grafo;
        this.numMaxArestas = numMaxArestas;
    }


       
    private void makeCombiUtil(ArrayList<Aresta<TIPO>> n, int left, int k) throws NumberFormatException, IOException {
        if (k == 0) {
            resultadosPossiveis.add(tmp);
            String[] resultado = grafo.geradorArvores(tmp, numMaxArestas).split(" ");
            if(resultado[0].equals("true")){
                resultadosValidos.add(tmp);
                for(int ii = 0; ii < tmp.size(); ii++){
                    System.out.print(tmp.get(ii) + " ");
                }
                System.out.println();
                grafo.escreveSolucao(tmp, Integer.parseInt(resultado[1]), "../src/arquivos/solucao" + count1 + ".txt");
                count1++;
            }
            /*for(int ii = 0; ii < tmp.size(); ii++){
                System.out.print(tmp.get(ii) + " ");
            }
            System.out.println();*/
            return;
        }
  
        for (int ii = left; ii < n.size(); ++ii){
            tmp.add(n.get(ii));
            makeCombiUtil(n, ii+1, k-1);
            tmp.remove(tmp.get(tmp.size() - 1));
        }
    }

    public void geraArvores() throws NumberFormatException, IOException{
        //makeCombiUtil(this.elementos, 0, this.frequencia);
        makeCombiUtil(grafo.getArestas(), 0, grafo.getNumVertices()-1);
    }

    public void geraArvoresValidas(){
    }

    public ArrayList<ArrayList<Aresta<TIPO>>> getResultadosPossiveis() {
        return this.resultadosPossiveis;
    }
}
