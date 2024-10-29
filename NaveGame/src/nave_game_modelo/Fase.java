package nave_game_modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import nave_game_sounds.EfeitosSonoros;
import nave_game_modelo.Explosao;
import nave_game_modelo.PowerUps;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Inimigo_1> inimigo1;
	private List<Estrela_1> estrela1;
	private List<Estrela_2> estrela2;
	private List<PowerUps> powerUpsVida;
	private List<Explosao> explosoes;
	private EfeitosSonoros musica;
	private int vida2 = 0, powerUpVida;
	private boolean emJogo;

	public Fase() {

		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon ref = new ImageIcon("res\\\\fundopreto.png");
		fundo = ref.getImage();
		
		musica = new EfeitosSonoros();
		SomFase();

		player = new Player();
		player.load();
		
		powerUpVida = 0;
		powerUpsVida = new ArrayList<PowerUps>();

		addKeyListener(new TecladoAdapter());

		timer = new Timer(3, this);
		timer.start();
		
		inicializaInimigos();
		inicializaEstrela();
		inicializaEstrela2();
		inicializaExplosoes();
		
		emJogo = true;
	}

	public void inicializaInimigos() {
		
		int coordenadas[] = new int[30];
		inimigo1 = new ArrayList<Inimigo_1>();
		
		for(int i = 0; i < coordenadas.length; i++) {
			int x = (int)(Math.random() * +510 - 20);
			int y = (int)(Math.random() * -5000 - 546);
			inimigo1.add(new Inimigo_1(x, y));
		}
	}
	
	public void inicializaEstrela() {
		
		int coordenadas[] = new int[500];
		estrela1 = new ArrayList<Estrela_1>();
		
		for(int i = 0; i < coordenadas.length; i++) {
			int x = (int)(Math.random() * +510 - 160);
			int y = (int)(Math.random() * -9000 + 400);
			estrela1.add(new Estrela_1(x, y));
		}
	}
	
	public void inicializaEstrela2() {
		
		int coordenadas[] = new int[150];
		estrela2 = new ArrayList<Estrela_2>();
		
		for(int i = 0; i < coordenadas.length; i++) {
			int x = (int)(Math.random() * +510 - 160);
			int y = (int)(Math.random() * -9000 + 400);
			estrela2.add(new Estrela_2(x, y));
		}
	}
	
	public void inicializaExplosoes() {
		explosoes = new ArrayList<Explosao>();

	}
	
	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;
		
		if(emJogo == true) {
			
			graficos.drawImage(fundo, 0, 0, null);
			
			for (int p = 0; p < estrela1.size(); p++) {
				Estrela_1 b = estrela1.get(p);
				b.load();
				graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
			}
			
			for (int a = 0; a < estrela2.size(); a++) {
				Estrela_2 c = estrela2.get(a);
				c.load();
				graficos.drawImage(c.getImagem(), c.getX(), c.getY(), this);
			}
			
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
			int a = 5;
			for (int j = 0; j < player.getVida(); j++) {
				ImageIcon vida = new ImageIcon("res\\Vida.png");
				graficos.drawImage(vida.getImage(), a, 20, null);
				a += 30;
			}
		}
		else {
			
			ImageIcon fimJogo = new ImageIcon("res\\gameover.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		}
		
		g.dispose();
	}
	
	public void SomFase() {
		musica.tocarFase();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		player.update();
		
		for(int p = 0; p < estrela1.size(); p++) {
			Estrela_1 on = estrela1.get(p);
			if(on.isVisivel()) {
				on.update();
			}
			else {
				estrela1.remove(p);
				}
		}
		
		for(int r = 0; r < estrela2.size(); r++) {
			Estrela_2 on = estrela2.get(r);
			if(on.isVisivel()) {
				on.update();
			}
			else {
				estrela2.remove(r);
				}
		}

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
		checarColisoes();
		repaint();
	}
	
	public void checarColisoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaInimigo1;
		Rectangle formaTiro;
		
		for(int i = 0; i < inimigo1.size(); i++) {
			Inimigo_1 tempInimigo_1 = inimigo1.get(i);
			formaInimigo1 = tempInimigo_1.getBounds();
			if(formaNave.intersects(formaInimigo1)) {
				player.setVisivel(false);
				tempInimigo_1.setVisivel(false);
				emJogo = false;
			}
		}
		
		List<Tiro> tiros = player.getTiros();
		for(int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);
			formaTiro = tempTiro.getBounds();
			for(int o = 0; o < inimigo1.size(); o++) {
				Inimigo_1 tempInimigo_1 = inimigo1.get(o);
				formaInimigo1 = tempInimigo_1.getBounds();
				
				if(formaTiro.intersects(formaInimigo1)) {
					tempInimigo_1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
		}
	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}
	}
}
