package nave_game_modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Estrela_2 {
	
	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;
	
	//private static final int ALTURA = 790;
	private static int VELOCIDADE = 1;
	
	public Estrela_2(int x, int y) {
		
		this.x = x;
		this.y = y;
		isVisivel = true;
	}
	
	public void load() {
		
		ImageIcon referencia = new ImageIcon("res\\estrelamedia2.png");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	
	public void update() {
		
		if(this.y > 600) { 
			
			this.y = altura;
			Random a = new Random();
			int m = a.nextInt(600);
			this.y = m + 1024;
			Random r = new Random();
			int n = r.nextInt(790);
			this.x = n;
		}
		else {
			
			this.y += VELOCIDADE;
		}
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
