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

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Inimigo_1> inimigo1;
	private List<Estrela_1> estrela1;
	private List<Estrela_2> estrela2;
	private List<Explosao> explosoes;
	private EfeitosSonoros musica;
	private boolean emJogo, emExplosao;

	public Fase() {

		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon ref = new ImageIcon("res\\fundopreto.png");
		fundo = ref.getImage();
		
		musica = new EfeitosSonoros();
		SomFase();

		player = new Player();
		player.load();

		addKeyListener(new TecladoAdapter());
		
		inicializaInimigos();
		inicializaEstrela();
		inicializaEstrela2();
		inicializaExplosoes();
		
		waitForSeconds();
		timer = new Timer(5, this);
		timer.start();
		
		emJogo = true;
	}

	public void inicializaInimigos() {
		
		int coordenadas[] = new int[30];
		inimigo1 = new ArrayList<Inimigo_1>();
		
		for(int i = 0; i < coordenadas.length; i++) {
			int x = (int)(Math.random() * +480 - 20);
			int y = (int)(Math.random() * -3000 - 546);
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
			for (int h = 0; h < explosoes.size(); h++) {
				Explosao ln = explosoes.get(h);
				ln.load();
				graficos.drawImage(ln.getImagem(), ln.getX(), ln.getY(), this);
			}
			
			for (int h = 0; h < explosoes.size(); h++) {
				Explosao ln = explosoes.get(h);
				ln.load();
				graficos.drawImage(ln.getImagem(), ln.getX(), ln.getY(), this);
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
				int inimigo, inimigo2;
				inimigo = inimigo1.get(o).getX();
				inimigo2 = inimigo1.get(o).getY();
				inimigo1.remove(o);
				explosoes.add(new Explosao(inimigo, inimigo2));
			}
		}
		
		for (int q = 0; q < explosoes.size(); q++) {
			Explosao y = explosoes.get(q);
			if (y.isVisivel()) {
				y.update();
			} else {
				explosoes.remove(q);
			}

		}
		checarColisoes();
		repaint();
	}
	
	public void waitForSeconds() {

		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < explosoes.size(); i++) {
					explosoes.get(i).setVisivel(false);
				}
			}
		});
		timer.start();
	}
	
	public void checarColisoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaInimigo1;
		Rectangle formaTiro;
		Rectangle formaChefao;
		Rectangle formaTiroBoss;
		
		for (int i = 0; i < inimigo1.size(); i++) {
	        Inimigo_1 tempInimigo_1 = inimigo1.get(i);
	        formaInimigo1 = tempInimigo_1.getBounds();

	        if (formaNave.intersects(formaInimigo1)) {
	            player.setVida(player.getVida() - 1);
	            musica.tocarSomDano();
	            ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
	            
	              emExplosao = true;
	            tempInimigo_1.setVisivel(false);

	            if (player.getVida() <= 0) {
	                player.setVisivel(false);
	                emJogo = false;
	            }
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
					musica.tocarSomExplosao();
					ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
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
