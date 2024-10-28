package nave_game_modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import nave_game_sounds.EfeitosSonoros;

public class Tiro {
	
	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;
	
	//private static final int ALTURA = 300;
	private static int VELOCIDADE = 5;
	
	public Tiro(int x, int y) {
		
		this.x = x;
		this.y = y;
		isVisivel = true;
		somTiroSimples();
	}
	
	public void load() {
		
		ImageIcon referencia = new ImageIcon("res\\tiro3.png");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	
	public void update() {
		
		this.y -= VELOCIDADE;
		
		if(this.y < 0) {
			isVisivel = false;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle (x, y, largura, altura);
	}

	public void somTiroSimples() {
		EfeitosSonoros a = new EfeitosSonoros();
		a.tocarTiro();
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
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
	
	
}
