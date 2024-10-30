package nave_game_modelo;

import javax.swing.*;

import nave_game_sounds.EfeitosSonoros;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;

public class Menu extends JPanel {
    
	private Image fundo;
	private EfeitosSonoros musica;
    private JButton botaoJogar;
    private boolean noMenu;

    public Menu(ActionListener iniciarJogoListener) {
        setLayout(new BorderLayout());
        
        setFocusable(true);
		setDoubleBuffered(true);

        ImageIcon ref = new ImageIcon("res\\MenuInicial.png");
		fundo = ref.getImage();
		
		musica = new EfeitosSonoros();
		SomFase();

        // Botão "Jogar"
		botaoJogar = new JButton("JOGAR");
		botaoJogar.setPreferredSize(new Dimension(100, 50));
        botaoJogar.addActionListener(e -> {
            musica.pararSomMenu(); // Para a música do menu ao clicar
            iniciarJogoListener.actionPerformed(e); // Inicia o jogo
        });
        
        add(botaoJogar, BorderLayout.SOUTH);
        
        noMenu = true;
    }

	private void SomFase() {
		
		musica.tocarSomMenu();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		
		if(noMenu) {
			
			graficos.drawImage(fundo, 0, 0, getWidth(), getHeight(), null);
		}
	}	
}