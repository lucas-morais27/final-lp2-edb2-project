package conjuntos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Grafos.Vertice;

public class ConjuntoDisjunto<TIPO> {
    private HashMap<Vertice<TIPO>, Vertice<TIPO>> raiz = new HashMap<>();
    private HashMap<Vertice<TIPO>, Integer> rank = new HashMap<>();
    private HashMap<Vertice<TIPO>, Integer> marcados = new HashMap<>();
    private int count1 = 0;
    private int count2 = 0;

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

        //System.out.println(vertice1.getQntArestasEntrada() + " " + vertice1.getQntArestasSaida());
        //System.out.println(vertice2.getQntArestasEntrada() + " " + vertice2.getQntArestasSaida());

        if(marcados.get(vertice1) == null){
            count1 = 0;
        }else{
            count1 = marcados.get(vertice1);
        }
        if(marcados.get(vertice2) == null){
            count2 = 0;
        }else{
            count2 = marcados.get(vertice2);
        }
        if(rank.get(x) < rank.get(y)){
            /*if(vertice2.getQntArestasEntrada() + vertice2.getQntArestasSaida() == numMaxArestas){
                return false;
            }*/
            if(marcados.containsKey(vertice2)){
                if(marcados.get(vertice2) == numMaxArestas){
                    return false;
                }
            }
            raiz.put(x, y);
            this.count1++;
            this.count2++;
            marcados.put(vertice1, count1);
            marcados.put(vertice2, count2);
        }else if(rank.get(x) > rank.get(y)){
            /*if(vertice1.getQntArestasEntrada() + vertice1.getQntArestasSaida() == numMaxArestas){
                return false;
            }*/
            if(marcados.containsKey(vertice1)){
                if(marcados.get(vertice1) == numMaxArestas){
                    return false;
                }
            }
            raiz.put(y, x);
            this.count1++;
            this.count2++;
            marcados.put(vertice1, count1);
            marcados.put(vertice2, count2);
        }else{
            /*if(vertice1.getQntArestasEntrada() + vertice1.getQntArestasSaida() == numMaxArestas){
                return false;
            }*/
            if(marcados.containsKey(vertice1) && marcados.containsKey(vertice2)){
                if(marcados.get(vertice1) == numMaxArestas || marcados.get(vertice2) == numMaxArestas){
                    return false;
                }
            }
            raiz.put(y, x);
            this.count1++;
            this.count2++;
            marcados.put(vertice1, count1);
            marcados.put(vertice2, count2);
            rank.put(x, rank.get(x)+1);
        }
        return true;
    };

    public void clearConjunto(){
        raiz.clear();
        marcados.clear();
        rank.clear();
        this.count1 = 0;
        this.count2 = 0;
    }

    public void imprimeConjunto(ArrayList<Vertice<TIPO>> vertices, ConjuntoDisjunto<TIPO> floresta){
        for(Vertice<TIPO> dado : vertices){
            System.out.print(floresta.encontraElemento(dado).getDado() + " ");
        }
        System.out.println();
    }

    public boolean validaConjunto(ArrayList<Vertice<TIPO>> vertices){
        Vertice<TIPO> temp1 = encontraElemento(vertices.get(0));
        for(Vertice<TIPO> dado : vertices){
            if(!encontraElemento(dado).getDado().equals(dado.getDado())){
                return false;
            }
        }
        return true;
    }
}