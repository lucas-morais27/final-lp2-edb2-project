package combinacoes;

import java.io.IOException;
import java.util.ArrayList;

import Grafos.Aresta;
import Grafos.Grafo;

public class ArvoresGeradoras<TIPO>{
    private ArrayList<ArrayList<Aresta<TIPO>>> resultadosTotais;
    private ArrayList<ArrayList<Aresta<TIPO>>> resultadosValidos;
    private ArrayList<Aresta<TIPO>> tmp;
    private Grafo<TIPO> grafo;
    private int count1=1;
    private int numMaxArestas;
    private int custoMinimo;

    public ArvoresGeradoras(Grafo<TIPO> grafo, int numMaxArestas) throws IOException {
        resultadosTotais = new ArrayList<ArrayList<Aresta<TIPO>>>();
        resultadosValidos = new ArrayList<ArrayList<Aresta<TIPO>>>();
        tmp = new ArrayList<Aresta<TIPO>>();
        this.grafo = grafo;
        this.numMaxArestas = numMaxArestas;
    }
       
    private void makeCombiUtil(ArrayList<Aresta<TIPO>> n, int left, int k) throws NumberFormatException, IOException {
        if (k == 0){
            resultadosTotais.add(tmp);
            String[] resultado = grafo.validaArvore(tmp, numMaxArestas).split(" ");
            if(resultado[0].equals("true")){
                resultadosValidos.add(tmp);
                if(count1 == 1){
                    custoMinimo = Integer.parseInt(resultado[1]);
                }else{
                    if(custoMinimo > Integer.parseInt(resultado[1])){
                        custoMinimo = Integer.parseInt(resultado[1]);
                    }
                }
                grafo.escreveSolucao(tmp, Integer.parseInt(resultado[1]), "../src/arquivos/solucao" + count1 + ".txt");
                count1++;
            }
            return;
        }
  
        for (int ii = left; ii < n.size(); ++ii){
            tmp.add(n.get(ii));
            makeCombiUtil(n, ii+1, k-1);
            tmp.remove(tmp.get(tmp.size() - 1));
        }
    }

    public void geraArvores() throws NumberFormatException, IOException{
        makeCombiUtil(grafo.getArestas(), 0, grafo.getNumVertices()-1);
    }
}
