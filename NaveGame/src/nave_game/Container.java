package nave_game;

import javax.swing.JFrame;

public class Container extends JFrame {
	
	public Container() {
		
		setTitle("Nave Game");//nome da janela do jogo
		setSize(1024, 728);//tamanho da janela (largura, altura)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//clicar no "x" fará a janela fechar
		setLocationRelativeTo(null);//a janela ficará no centralizada na tela
		this.setResizable(false);//usuário não pode mudar o tamanho da janela
		setVisible(true);//todas as configurações do constructor são visíveis
	}
	
	public static void main(String []args) {
		
		new Container();
	}
}
