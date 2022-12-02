package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import grafos.Grafo;

public class CreateGraphInterface {
    private JFrame frame;
    
    public CreateGraphInterface(Grafo<String> grafo) {
        estilizarFrame(grafo);
    }

    public void estilizarFrame(Grafo<String> grafo) {
        frame = new JFrame("Trabalho LP2-EDB2");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,250);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JButton botao1 = new JButton("Grafo");
        JButton botao2 = new JButton("Arvore Geradora Minima");

        botao1.setBackground(Color.RED);
        botao2.setBackground(Color.RED);
        botao1.setForeground(Color.WHITE);
        botao2.setForeground(Color.WHITE);
        botao1.setBounds(100, 100, 100, 40);
        botao2.setBounds(300, 100, 200, 40);

        frame.add(botao1);
        frame.add(botao2);

        botao1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               grafo.mostraInterfaceGrafica(); 
            }
        });

        botao2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               grafo.mostraArvoreGeradoraMinima(); 
            }
        });
    }

    public void start() {
        frame.setVisible(true);
    }
}
