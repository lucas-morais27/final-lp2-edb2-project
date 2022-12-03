package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import grafos.Grafo;

public class CreateGraphInterface {
    private JFrame frame;
    private JButton botao1 = new JButton("Grafo");
    private JButton botao2 = new JButton("Árvore Geradora Mínima");
    
    public CreateGraphInterface(Grafo<String> grafo) {
        setFrame();
        setBotao1(grafo);
        setBotao2(grafo);
    }

    private JButton getBotao1() {
        return this.botao1;
    }

    private void setBotao1(Grafo<String> grafo) {
        this.botao1.setBackground(Color.RED);
        this.botao1.setForeground(Color.WHITE);
        this.botao1.setBounds(100, 100, 100, 40);

        this.botao1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               grafo.mostraInterfaceGrafica(); 
            }
        });
    }

    private JButton getBotao2() {
        return this.botao2;
    }

    private void setBotao2(Grafo<String> grafo) {
        this.botao2.setBackground(Color.RED);
        this.botao2.setForeground(Color.WHITE);
        this.botao2.setBounds(300, 100, 200, 40);

        this.botao2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               grafo.mostraArvoreGeradoraMinima(); 
            }
        });
    }

    private void setFrame() {
        frame = new JFrame("Trabalho LP2-EDB2");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,250);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.add(getBotao1());
        frame.add(getBotao2());
    }

    public void startFrame() {
        frame.setVisible(true);
    }
}
