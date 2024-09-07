package nave_game_modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fase extends JPanel {
	
	private Image fundo;
	
	public Fase() {
		ImageIcon ref = new ImageIcon("res\\\\fundopreto.png");
		fundo = ref.getImage();
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo,0,0,null);
		g.dispose();
	}
}
