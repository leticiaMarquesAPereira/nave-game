package nave_game;

import nave_game_modelo.Fase;
import nave_game_modelo.Menu;

import javax.swing.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Container extends JFrame {

    private Menu menu;
    private Fase fase;
    private Image fundo;

    public Container() {
        inicializaContainer();
    }

    private void inicializaContainer() {
        setTitle("LETUXA GAME");
        setSize(450, 580); // tamanho da janela (largura, altura)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza a janela na tela
        setResizable(false); // usuário não pode redimensionar a janela

        // Inicializa o menu com a ação para iniciar o jogo
        menu = new Menu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            } 
        });

        // Adiciona o menu à tela inicialmente
        add(menu);
        setVisible(true); // torna visíveis todas as configurações do constructor
    }

    private void iniciarJogo() {
        // Remove o menu e adiciona a fase do jogo
        remove(menu);
        setSize(546, 768);
        setLocationRelativeTo(null); 
        setResizable(false);
        fase = new Fase();
        add(fase);

        // Atualiza a interface para exibir a fase do jogo
        revalidate();
        repaint();

        fase.requestFocusInWindow(); // garante que a fase receba o foco para teclas
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Container container = new Container();
            container.setVisible(true);
        });
    }
}