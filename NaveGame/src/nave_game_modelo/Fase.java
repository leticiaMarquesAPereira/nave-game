package nave_game_modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Inimigo_1> inimigo1;

	public Fase() {

		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon ref = new ImageIcon("res\\\\fundopreto.png");
		fundo = ref.getImage();

		player = new Player();
		player.load();

		addKeyListener(new TecladoAdapter());

		timer = new Timer(3, this);
		timer.start();
		
		inicializaInimigos();
	}

	public void inicializaInimigos() {
		
		int coordenadas[] = new int[50];
		inimigo1 = new ArrayList<Inimigo_1>();
		
		for(int i = 0; i < coordenadas.length; i++) {
			int x = (int)(Math.random() * +765 + 30);
			int y = (int)(Math.random() * -8000 - 500);
			inimigo1.add(new Inimigo_1(x, y));
		}
	}
	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			m.load();
			graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
		}
		
		for(int o = 0; o < inimigo1.size(); o++) {
			Inimigo_1 in = inimigo1.get(o);
			in.load();
			graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		player.update();

		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);

			if (m.isVisivel()) {
				m.update();
			} else {
				tiros.remove(i);
			}
		}
		
		for(int o = 0; o < inimigo1.size(); o++) {
			Inimigo_1 in = inimigo1.get(o);
			if(in.isVisivel()) {
				in.update();
			}
			else {
				inimigo1.remove(o);
			}
		}

		repaint();
	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}
	}
}
