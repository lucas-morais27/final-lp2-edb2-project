import java.io.IOException;
import Grafos.*;

public class App {
    public static void main(String[] args) throws IOException {
        Input arquivo = new Input(args[0]);
        arquivo.buildGrafo();
        Grafo<String> grafo = arquivo.getGrafo();
        //grafo.arvoreGeradoraMinima(arquivo.getNumMaxArestas());
    }
}
