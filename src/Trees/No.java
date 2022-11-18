package Trees;

import java.util.ArrayList;

public class No {
    public int valor;   // valor do nó
    public ArrayList<No> filhos; 

    public No(int valor){
        this.valor = valor;
        this.filhos = new ArrayList<>();
    }
}
