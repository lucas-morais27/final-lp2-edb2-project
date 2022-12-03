package conjuntos;

import java.util.ArrayList;
import java.util.HashMap;

import grafos.Vertice;

public class ConjuntoDisjunto<TIPO> {
    private HashMap<Vertice<TIPO>, Vertice<TIPO>> raiz = new HashMap<>();
    private HashMap<Vertice<TIPO>, Integer> rank = new HashMap<>();
    private HashMap<Vertice<TIPO>, Integer> marcados = new HashMap<>();

    public void criaConjunto(ArrayList<Vertice<TIPO>> vertices) {
        for (Vertice<TIPO> dado : vertices) {
            raiz.put(dado, dado);
            rank.put(dado, 0);
        }
    };
    
    private Vertice<TIPO> encontraElemento(Vertice<TIPO> vertice) {
        if (!raiz.get(vertice).getDado().equals(vertice.getDado())) {
            vertice = encontraElemento(raiz.get(vertice));
        }
        return vertice;
    };

    public boolean uneElementos(Vertice<TIPO> vertice1, Vertice<TIPO> vertice2, int numMaxArestas) {
        Vertice<TIPO> auxVertice1 = encontraElemento(vertice1);
        Vertice<TIPO> auxVertice2 = encontraElemento(vertice2);
        int count1 = 0;
        int count2 = 0;

        if (auxVertice1.getDado().equals(auxVertice2.getDado())) {
            return false;
        }

        if (marcados.get(vertice1) == null) {
            count1 = 0;
        } else {
            count1 = marcados.get(vertice1);
        }
        if (marcados.get(vertice2) == null) {
            count2 = 0;
        } else {
            count2 = marcados.get(vertice2);
        }

        if (rank.get(auxVertice1) < rank.get(auxVertice2)) {
            if (marcados.containsKey(vertice2)) {
                if (marcados.get(vertice2) == numMaxArestas) {
                    return false;
                }
            }
            raiz.put(auxVertice1, auxVertice2);
            count1++;
            count2++;
            marcados.put(vertice1, count1);
            marcados.put(vertice2, count2);
        } else if (rank.get(auxVertice1) > rank.get(auxVertice2)) {
            if (marcados.containsKey(vertice1)) {
                if (marcados.get(vertice1) == numMaxArestas) {
                    return false;
                }
            }
            raiz.put(auxVertice2, auxVertice1);
            count1++;
            count2++;
            marcados.put(vertice1, count1);
            marcados.put(vertice2, count2);
        } else {
            if (marcados.containsKey(vertice1) && marcados.containsKey(vertice2)) {
                if (marcados.get(vertice1) == numMaxArestas || marcados.get(vertice2) == numMaxArestas) {
                    return false;
                }
            }
            raiz.put(auxVertice2, auxVertice1);
            count1++;
            count2++;
            marcados.put(vertice1, count1);
            marcados.put(vertice2, count2);
            rank.put(auxVertice1, rank.get(auxVertice1)+1);
        }
        return true;
    };
}