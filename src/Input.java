import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import Grafos.*;

public class Input {
    private Grafo<String> grafo = new Grafo<>();
    private int numCasas;
    private int numMaxArestas;
    private List<String> linhas = new LinkedList<>();

    public Input(String entrada) throws IOException {
        FileReader fileRead = new FileReader(entrada);
        BufferedReader buffRead = new BufferedReader(fileRead);
		String linha = "";
		while (true) {
            linha = buffRead.readLine();
			if (linha != null) {
				this.linhas.add(linha);
			} else {
                break;
            }		
		}
        try {
            buffRead.close();
        } catch (Exception e) {
            System.out.println("Arquivo não pôde ser fechado.");
        }
		
    }

    public int getNumCasas(){
        return this.numCasas;
    }

    public int getNumMaxArestas(){
        return this.numMaxArestas;
    }

    public Grafo<String> getGrafo(){
        return this.grafo;
    }

    public void buildGrafo() {
        for (int ii = 0; ii < linhas.size(); ii++) {
            if (ii == 0) {
                numCasas = Integer.parseInt(linhas.get(ii).split(" ")[0]);

                for (int jj = 0; jj < numCasas; jj++) {
                    grafo.addVertice("Casa" + jj);
                }

                numMaxArestas = Integer.parseInt(linhas.get(ii).split(" ")[1]);
            } else {
                String[] custos = linhas.get(ii).split(" ");
                int count = ii;
                for(var item : custos){
                    grafo.addAresta(Integer.parseInt(item), "Casa" + ii, "Casa" + (count+1));
                    count++;
                }
            }
        }
    }
}