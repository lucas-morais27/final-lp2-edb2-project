package conjuntos;

import java.util.ArrayList;
import java.util.HashMap;

import grafos.Vertice;

public class ConjuntoDisjunto<TIPO> {
    private HashMap<Vertice<TIPO>, Vertice<TIPO>> raiz = new HashMap<>();
    private HashMap<Vertice<TIPO>, Integer> rank = new HashMap<>();

    public void criaConjunto(ArrayList<Vertice<TIPO>> vertices){
        for(Vertice<TIPO> dado : vertices){
            raiz.put(dado, dado);
            rank.put(dado, 0);
        }
    };
    
    public Vertice<TIPO> encontraElemento(Vertice<TIPO> vertice){
        if(!raiz.get(vertice).getDado().equals(vertice.getDado())){
            vertice = encontraElemento(raiz.get(vertice));
        }
        return vertice;
    };

    public int getRank(Vertice<TIPO> vertice){
        Vertice<TIPO> x = encontraElemento(vertice);
        return rank.get(x);
    };

    public boolean uneElementos(Vertice<TIPO> vertice1, Vertice<TIPO> vertice2, int numMaxArestas){
        Vertice<TIPO> x = encontraElemento(vertice1);
        Vertice<TIPO> y = encontraElemento(vertice2);

        if(x.getDado().equals(y.getDado())){
            return false;
        }

        if(rank.get(x) < rank.get(y)){
            if(vertice2.getQntArestasEntrada() + vertice2.getQntArestasSaida() == numMaxArestas){
                return false;
            }
            raiz.put(x, y);
        }else if(rank.get(x) > rank.get(y)){
            if(vertice1.getQntArestasEntrada() + vertice1.getQntArestasSaida() == numMaxArestas){
                return false;
            }
            raiz.put(y, x);
        }else{
            if(vertice1.getQntArestasEntrada() + vertice1.getQntArestasSaida() == numMaxArestas){
                return false;
            }
            raiz.put(y, x);
            rank.put(x, rank.get(x)+1);
        }
        return true;
    };
}