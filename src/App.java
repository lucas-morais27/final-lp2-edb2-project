import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        List<String> comandos = new LinkedList<>();
        String linha;
        while(input.hasNextLine()){
            linha = input.nextLine();
            comandos.add(linha);
        };
        comandos.forEach(dado -> System.out.println(dado));
        input.close();
    }
}
