package main;

import java.io.IOException;

import grafos.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Input arquivo = new Input(args[0]);         // leitura do arquivo de entrada
        Grafo<String> grafo = arquivo.getGrafo();   // cria o grafo
        grafo.criaArvores();            // encontra as arvores geradoras do grafo
        var graphInterface = new CreateGraphInterface(grafo);   // cria a interface gráfica
        graphInterface.startFrame();    // mostra a interface gráfica
    }
}
