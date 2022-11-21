package Trees;

import java.util.LinkedList;

public class No<TIPO> {
    public TIPO valor;   // valor do nó
    public LinkedList<No<TIPO>> filhos;

    public No(TIPO valor){
        this.valor = valor;
        this.filhos = new LinkedList<>();
    }

    public void setFilhos(No<TIPO> filho) {
        this.filhos.add(filho);
    }
}
