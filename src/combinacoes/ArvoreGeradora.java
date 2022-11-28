package combinacoes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Grafos.Aresta;
import Grafos.Grafo;
import Grafos.Vertice;
import conjuntos.ConjuntoDisjunto;

public class ArvoreGeradora<TIPO> extends Grafo<TIPO>{
    private ArrayList<Aresta<TIPO>> arestas;
    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> tmp;
    private int numMaxArestas;
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
            String[] resultado = validaArvore(tmp, numMaxArestas).split(" ");
            if(resultado[0].equals("true")){
                if(custoArvoreMinima == 0){
                    custoArvoreMinima = Integer.parseInt(resultado[1]);
                }else{
                    if(custoArvoreMinima > Integer.parseInt(resultado[1])){
                        custoArvoreMinima = Integer.parseInt(resultado[1]);
                    }
                }
                escreveSolucao(tmp, Integer.parseInt(resultado[1]), "../src/arquivos/solucao.txt");
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
        escreveArvoreMinima("../src/arquivos/solucao.txt");
    }

    public void escreveArvoreMinima(String entrada) throws IOException {
        List<String> linhas = new LinkedList<>();
        FileReader fileRead = new FileReader(entrada);
        BufferedReader buffRead = new BufferedReader(fileRead);
		String linha = "";
		while (true) {
            linha = buffRead.readLine();
			if (linha != null) {
				linhas.add(linha);
			} else {
                break;
            }		
		}

        var vetorString = new LinkedList<String>();

        for (var s : linhas){
            char c = s.charAt(0); 
            if (Character.isLetter(c)){
                vetorString.add(s);
            }else{
                if (Character.isDigit(c)) {
                    if(Integer.parseInt(s) == this.custoArvoreMinima){
                        vetorString.add(s);
                        escreveSolucao(vetorString);
                        break;
                    } else {
                        vetorString.clear();
                    }
                }
            }
        }

        try {
            fileRead.close();
            buffRead.close();
        } catch (Exception e1) {
            System.out.println("Arquivo não pôde ser fechado e/ou buffer não pode ser fechado.");
        }
    }
    
    public String validaArvore(ArrayList<Aresta<TIPO>> arvoreEmPotencial, int numMaxArestas) throws IOException{
        ConjuntoDisjunto<TIPO> floresta = new ConjuntoDisjunto<>();
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
            //floresta.clearConjunto();
            return "false" + " " + 0;
        }else{
            //floresta.clearConjunto();
            return "true" + " " + custoTotal;
        }   
    }
}
