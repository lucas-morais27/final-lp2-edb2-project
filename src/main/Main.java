package main;

import java.io.IOException;

import grafos.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Input arquivo = new Input(args[0]);
        Grafo<String> grafo = arquivo.getGrafo();
        grafo.criaArvores();
        var graphInterface = new CreateGraphInterface(grafo);
        graphInterface.startFrame();
    }
}
