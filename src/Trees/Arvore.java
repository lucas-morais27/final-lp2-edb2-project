package Trees;

public class Arvore<TIPO> {
    private No<TIPO> raiz = null;

    public void inserirNo(TIPO valor){
        if(raiz == null){ // se true, inicializa a raiz da arvore
            raiz = new No<TIPO>(valor);
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
            anterior.setFilhos(novo);
        }
    }

    private void diagramaBarras(No<TIPO> raiz, String espacamento){ // imprime a arvore na representação de diagrama de barras
        if(raiz != null){
            System.out.print(espacamento + raiz.valor); // imprime o espaçamento, que depende da profundidade na arvore, e o valor do nó
            for(int ii=0; ii < ((this.raiz.filhos.size()*5) - espacamento.length()); ii++){
                if(ii == ((this.raiz.filhos.size()*5) - espacamento.length() - 1)){
                    System.out.print("-\n");
                }else{
                    System.out.print("-");
                }
            }
            diagramaBarras(raiz.filhos.remove(0), espacamento+"    "); // imprime a sub-arvore à esquerda da raiz
        }else{ // arvore vazia
            return;
        }
    }
}
