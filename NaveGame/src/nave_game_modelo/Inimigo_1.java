package nave_game_modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import nave_game_modelo.Explosao;

public class Inimigo_1 extends Nave implements ActionListener{
	
	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;
	private List<Explosao> explosoes;
	
	private Timer timer;
	
	private static final int ALTURA = 790;
	private static int VELOCIDADE = 3;
	
	public Inimigo_1(int x, int y) {
		
		this.x = x;
		this.y = y;
		isVisivel = true;
		
		explosoes = new ArrayList<Explosao>();
		
		timer = new Timer(1500, this);
		timer.start();
	}
	
	public void explosoes() {
		this.explosoes.add(new Explosao(x + largura, y + altura / 2));

	}
	
	public void load() {
		
		ImageIcon referencia = new ImageIcon("res\\naveinimiga_1.png");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	
	public void update() {
		
		this.y += VELOCIDADE;
		
		//if(this.y > ALTURA) {
		//	isVisivel = false;
		//}
	}
	
	public Rectangle getBounds() {
		return new Rectangle (x, y, largura, altura);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
