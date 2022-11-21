package Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class No<TIPO> {
    public TIPO valor;   // valor do nó
    public Map<Integer, No> filhos;

    public No(TIPO valor){
        this.valor = valor;
        this.filhos = new HashMap<>();
    }

    public void setFilhos(int custo, No filho) {
        this.filhos.put(custo, filho);
    }
}
