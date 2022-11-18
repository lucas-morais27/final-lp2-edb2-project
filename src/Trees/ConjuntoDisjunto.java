package Trees;

import java.util.ArrayList;
import java.util.HashMap;

import Grafos.Vertice;

public class ConjuntoDisjunto{
    private HashMap<Vertice<String>, Vertice<String>> raiz = new HashMap<>();

    public void criaConjunto(ArrayList<Vertice<String>> vertices){
        for(Vertice<String> dado : vertices){
            raiz.put(dado, dado);
        }
    };

    public Vertice<String> encontraElemento(Vertice<String> vertice){
        if(raiz.get(vertice) == vertice){
            return vertice;
        }
        return encontraElemento(raiz.get(vertice));
    };

    public void uneElementos(Vertice<String> vertice1, Vertice<String> vertice2){
        Vertice<String> x = encontraElemento(vertice1);
        Vertice<String> y = encontraElemento(vertice2);
        raiz.put(x, y);
    };
}