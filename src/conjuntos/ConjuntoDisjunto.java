package conjuntos;

import java.util.ArrayList;
import java.util.HashMap;

import grafos.Vertice;

public class ConjuntoDisjunto<TIPO> {
    private HashMap<Vertice<TIPO>, Vertice<TIPO>> raiz = new HashMap<>();
    private HashMap<Vertice<TIPO>, Integer> marcados = new HashMap<>();
    private HashMap<Vertice<TIPO>, Integer> rank = new HashMap<>();

    public void criaConjunto(ArrayList<Vertice<TIPO>> vertices) {
        for (Vertice<TIPO> dado : vertices) { // cada vertice vira uma arvore/conjunto disjunto unitario
            raiz.put(dado, dado);
            rank.put(dado, 0);
        }
    };
    
    private Vertice<TIPO> encontraElemento(Vertice<TIPO> vertice) { // retorna o representante do conjunto
        if (!raiz.get(vertice).getDado().equals(vertice.getDado())) { 
            vertice = encontraElemento(raiz.get(vertice));
        }
        return vertice;
    };

    public boolean uneElementos(Vertice<TIPO> vertice1, Vertice<TIPO> vertice2, int numMaxArestas) {
        Vertice<TIPO> auxVertice1 = encontraElemento(vertice1); // encontra o representante de vertice1
        Vertice<TIPO> auxVertice2 = encontraElemento(vertice2); // encontra o representante de vertice2
        int count1 = 0;
        int count2 = 0;

        if (auxVertice1.getDado().equals(auxVertice2.getDado())) { // caso possuam o mesmo representante/pai, ou seja, pertencem ao mesmo conjunto/arvore
            return false; // união formaria um ciclo
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

        if (rank.get(auxVertice1) < rank.get(auxVertice2)) { // se a arvore/conjunto que vertice1 está presente for menor que a de vertice2, une vertice2 com vertice1
            if (marcados.containsKey(vertice2)) {
                if (marcados.get(vertice2) == numMaxArestas) { // verificação da restrição de aresta
                    return false; // união descumpriria a restrição de aresta
                }
            }
            raiz.put(auxVertice1, auxVertice2); // união
            count1++;
            count2++;
            marcados.put(vertice1, count1); // marcação do vertice1 e quantidade de ligações estabelecidadas
            marcados.put(vertice2, count2); // marcação do vertice2 e quantidade de ligações estabelecidadas
        } else if (rank.get(auxVertice1) > rank.get(auxVertice2)) { // se a arvore/conjunto que vertice1 está presente for maior que a de vertice2, une vertice1 com vertice2
            if (marcados.containsKey(vertice1)) {
                if (marcados.get(vertice1) == numMaxArestas) { // verificação da restrição de aresta
                    return false; // união descumpriria a restrição de aresta
                }
            }
            raiz.put(auxVertice2, auxVertice1); // união 
            count1++;
            count2++;
            marcados.put(vertice1, count1); // marcação do vertice1 e quantidade de ligações estabelecidadas
            marcados.put(vertice2, count2); // marcação do vertice1 e quantidade de ligações estabelecidadas
        } else { // caso a arvore/conjunto que vertice1 está presente seja de mesmo tamanho que a de vertice2, une vertice1 com vertice2
            if (marcados.containsKey(vertice1) && marcados.containsKey(vertice2)) {
                if (marcados.get(vertice1) == numMaxArestas || marcados.get(vertice2) == numMaxArestas) { // verificação da restrição de aresta
                    return false; // união descumpriria a restrição de aresta
                }
            }
            raiz.put(auxVertice2, auxVertice1); // união
            count1++;
            count2++;
            marcados.put(vertice1, count1); // marcação do vertice1 e quantidade de ligações estabelecidadas
            marcados.put(vertice2, count2); // marcação do vertice1 e quantidade de ligações estabelecidadas
            rank.put(auxVertice1, rank.get(auxVertice1)+1); // Incremento do rank da arvore (profundidade)
        }
        return true;
    };
}