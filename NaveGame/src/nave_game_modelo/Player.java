package nave_game_modelo;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {
	
	private int x, y;
	private int dx, dy;
	private Image imagem;
	private List <Tiro> tiros;
	
	private int altura, largura;
	
	public Player() {
		
		this.x = 100;
		this.y = 100;
		
		tiros = new ArrayList<Tiro>();
	}
	
	public void load() {
		
		ImageIcon referencia = new ImageIcon("res\\nave3.png");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}
	
	public void update() {
		
		x += dx;
		y += dy;
	}
	
	public void tiroSimples() {
		
		this.tiros.add(new Tiro(x + (largura / 2), y));
	}
	
	public void keyPressed(KeyEvent tecla) {
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_A) {
			tiroSimples();
		}
		if(codigo == KeyEvent.VK_UP) {
			dy = -3;
		}
		if(codigo == KeyEvent.VK_DOWN) {
			dy = 3;
		}
		if(codigo == KeyEvent.VK_RIGHT) {
			dx = 3;
		}
		if(codigo == KeyEvent.VK_LEFT) {
			dx = -3;
		}
	}
	
	public void keyRelease(KeyEvent tecla) {
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP) {
			dy = 0;
		}
		if(codigo == KeyEvent.VK_DOWN) {
			dy = 0;
		}
		if(codigo == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		if(codigo == KeyEvent.VK_LEFT) {
			dx = 0;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}
	
	
	
}
