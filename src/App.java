import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import Grafos.*;

public class App {
    public static void main(String[] args) throws Exception {
        /*Scanner input = new Scanner(System.in);
        List<String> comandos = new LinkedList<>();
        String linha;
        while(input.hasNextLine()){
            linha = input.nextLine();
            comandos.add(linha);
        };
        comandos.forEach(dado -> System.out.println(dado));
        input.close();*/

        Grafo<String> grafo = new Grafo<>();
        grafo.addVertice("Casa1");
        grafo.addVertice("Casa2");
        grafo.addVertice("Casa3");
        grafo.addVertice("Casa4");
        grafo.addVertice("Casa5");

        grafo.addAresta(5, "Casa1", "Casa2");
        grafo.addAresta(2, "Casa2", "Casa3");
        grafo.addAresta(53, "Casa3", "Casa4");
        grafo.addAresta(13, "Casa4", "Casa5");

        System.out.println(grafo.getAresta(2).getCusto() + " " + grafo.getAresta(2).getInicio().getDado() + " " + grafo.getAresta(2).getFim().getDado());



    }
}
