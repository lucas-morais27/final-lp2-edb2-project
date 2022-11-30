import java.io.File;
import java.io.IOException;
import Grafos.*;
 

public class App {
    public static void main(String[] args) throws IOException {
        Input arquivo = new Input(args[0]);

        File arquivoSolucao = new File("src/arquivos/solucao.txt");
        if(arquivoSolucao.exists()){
            arquivoSolucao.delete();
        }
        
        Grafo<String> grafo = arquivo.getGrafo();
        grafo.allArvoresGeradoras();
        grafo.mostraInterfaceGrafica();
        grafo.mostraArvoreGeradoraMinima();
        
    }
}
