package Trees;

import java.util.ArrayList;
import java.util.HashMap;

import Grafos.Vertice;

public class ConjuntoDisjunto<TIPO> {
    private HashMap<Vertice<TIPO>, Vertice<TIPO>> raiz = new HashMap<>();

    public void criaConjunto(ArrayList<Vertice<TIPO>> vertices){
        for(Vertice<TIPO> dado : vertices){
            raiz.put(dado, dado);
        }
    };

    public Vertice<TIPO> encontraElemento(Vertice<TIPO> vertice){
        if(raiz.get(vertice) == vertice){
            return vertice;
        }
        return encontraElemento(raiz.get(vertice));
    };

    public void uneElementos(Vertice<TIPO> vertice1, Vertice<TIPO> vertice2){
        Vertice<TIPO> x = encontraElemento(vertice1);
        Vertice<TIPO> y = encontraElemento(vertice2);
        raiz.put(x, y);
    };
}