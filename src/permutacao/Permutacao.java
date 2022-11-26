package permutacao;

import java.util.ArrayList;

import Grafos.Aresta;

public class Permutacao<TIPO>{
    private int cont=0;
    private ArrayList<Aresta<TIPO>> perm;

    public void permuta(ArrayList<Aresta<TIPO>> vet) {
		perm = new ArrayList<>(vet.size());
		permuta(vet,0);
	}

    private void imprime(){
		System.out.println();
		System.out.print("(" + cont + ") : ");
		for (int i=0; i < perm.size(); i++){
            System.out.print(perm.get(i).getInicio().getDado() + "-->" + perm.get(i).getFim().getDado() + " ");
        }
    }

    private void permuta(ArrayList<Aresta<TIPO>> vet, int n) {
		if (n==vet.size()){
			cont++;
            imprime();
		} else {
			for (int i=0; i < vet.size(); i++) {
				boolean achou = false;
				for(int j = 0; j < n; j++){
					if(perm.get(j).equals(vet.get(i))){
                       achou = true; 
                    } 
				}
				if(!achou){
                    perm.add(n, vet.get(i));
                    if(perm.size() > 3){
                      perm.remove(n+1);  
                    }
					permuta(vet, n+1);
				}

			}

		}

	}
}
