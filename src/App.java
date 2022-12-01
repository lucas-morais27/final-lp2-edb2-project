import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import Grafos.*;
 

public class App {
    public static void main(String[] args) throws IOException {
        Input arquivo = new Input(args[0]);

        File arquivoSolucao = new File("src/arquivos/solucao.txt");
        if(arquivoSolucao.exists()){
            arquivoSolucao.delete();
        }
        
        Grafo<String> grafo = arquivo.getGrafo();
        grafo.allArvoresGeradoras();
        
        JFrame frame = new JFrame("Trabalho LP2-EDB2");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,250);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLocationRelativeTo(null);
        JButton button = new JButton("Grafo");
        JButton button2 = new JButton("Arvore Geradora Minima");
        button.setBackground(Color.RED);
        button2.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button2.setForeground(Color.WHITE);
        frame.setLayout(null);

        button.setBounds(100, 100, 100, 40);
        button2.setBounds(300, 100, 200, 40);
        frame.add(button);
        frame.add(button2);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               grafo.mostraInterfaceGrafica(); 
            }
        });

        button2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               grafo.mostraArvoreGeradoraMinima(); 
            }
        });

        frame.setVisible(true);
    }
}
