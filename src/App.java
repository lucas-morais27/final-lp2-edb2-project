import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Grafos.*;

public class App {
    public static void main(String[] args) throws IOException {
        /*Scanner input = new Scanner(System.in);
        List<String> comandos = new LinkedList<>();
        String linha;
        while(input.hasNextLine()){
            linha = input.nextLine();
            comandos.add(linha);
        };
        input.close();
        int numCasas = 0, numMaxArestas = 0;
        Grafo<String> grafo = new Grafo<>();
        for(int ii=0; ii < comandos.size(); ii++){
            if(ii==0){
                numCasas = Integer.parseInt(comandos.get(ii).split(" ")[0]);
                for(int bb=1; bb <= numCasas; bb++){
                    grafo.addVertice("Casa" + bb);
                }
                numMaxArestas = Integer.parseInt(comandos.get(ii).split(" ")[1]);
            }else{
                String[] custos = comandos.get(ii).split(" ");
                int count = ii;
                for(String dado : custos){
                    grafo.addAresta(Integer.parseInt(dado), "Casa" + ii, "Casa" + (count+1));
                    count++;
                }
            }
        }*/
        Input arquivo = new Input(args[0]);
        Grafo<String> grafo = arquivo.getGrafo();
        //grafo.arvoreGeradoraMinima(numMaxArestas);
        grafo.allArvoresGeradoras();
    }
}
