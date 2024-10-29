package nave_game_modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player extends Nave implements ActionListener{

    private int x, y;
    private int dx, dy;
    private Image imagem;
    private List<Tiro> tiros;
    private boolean isVisivel;
    private Timer timer;
    private int vida;

    private int altura, largura;

    // Variáveis para controlar as teclas pressionadas
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean rightPressed = false;
    private boolean leftPressed = false;
    private boolean shootPressed = false;

    public Player() {
        this.x = 240;
        this.y = 375;
        
        vida = 3;
        isVisivel = true;
		timer = new Timer(10000, this);
		timer.start();

        tiros = new ArrayList<Tiro>();
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {
	}

    public void load() {
        ImageIcon referencia = new ImageIcon("res\\nave3.png");
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }
    
    //Limita a tela onde a nave pode ir
    public void update() {
        processMovement();
        x += dx;
        y += dy;
        
        if (this.x < 6) {
            x = 6;
        }
        if (this.x > 482) {
            x = 482;
        }
        if (this.y < 6) {
            y = 6;
        }
        if (this.y > 680) {
            y = 680;
        }
    }

    public void tiroSimples() {
        this.tiros.add(new Tiro(x + (largura / 2), y));
    }

    //Método que processa o movimento com base no estado das teclas
    private void processMovement() {
        dx = 0; //Reinicializa dx e dy antes de processar
        dy = 0;

        if (upPressed) {
            dy = -5;
        }
        if (downPressed) {
            dy = 5;
        }
        if (rightPressed) {
            dx = 5;
        }
        if (leftPressed) {
            dx = -5;
        }
    }

    // Método que trata quando uma tecla é pressionada
    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (codigo == KeyEvent.VK_SPACE) {
            shootPressed = true;
            tiroSimples();  // Dispara imediatamente ao pressionar espaço
        }
    }

    // Método que trata quando uma tecla é solta
    public void keyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (codigo == KeyEvent.VK_SPACE) {
            shootPressed = false;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
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
    
    public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
}
