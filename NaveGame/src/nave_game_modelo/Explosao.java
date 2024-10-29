package nave_game_modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import nave_game_sounds.EfeitosSonoros;

public class Explosao {

	private Image Imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;
	private int tempoDeVisibilidade;

	private static final int LARGURA = 6;
	private static int VELOCIDADE = 2;

	public Explosao(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
		this.tempoDeVisibilidade = 100;
		SomExplosao();
		load();
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("res\\explosion1.gif");
		Imagem = referencia.getImage();

		this.altura = Imagem.getHeight(null);
		this.largura = Imagem.getWidth(null);

	}

	public void setX(int x) {
		this.x = x;
	}

	public void update() {
	    if (tempoDeVisibilidade > 0) {
	        tempoDeVisibilidade--;
	    } else {
	        isVisivel = false;
	    }
	}

	public void SomExplosao() {
		EfeitosSonoros a = new EfeitosSonoros();
		a.tocarSomExplosao();
	}

	public void setImagem(Image imagem) {
		Imagem = imagem;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return Imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}