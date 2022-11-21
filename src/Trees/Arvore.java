package Trees;

public class Arvore<TIPO> {
    private No<TIPO> raiz = null;

    public void inserirNo(TIPO valor, int custo){
        if(raiz == null){ // se true, inicializa a raiz da arvore
            raiz = new No<>(valor);
        }else{ // busca o local correto para inserir o novo elemento caso não seja um elemento repetido
            No<TIPO> atual = raiz;
            No<TIPO> anterior = null;
            while(atual != null){
                anterior = atual;
                if(valor == atual.valor){ // o valor já existe na arvore
                    //System.out.println(valor + " já está na árvore, não pode ser inserido");
                    return;
                }
            }
            No<TIPO> novo = new No<>(valor);
            anterior.setFilhos(custo, novo);
        }
    }
}
