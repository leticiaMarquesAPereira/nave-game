package nave_game_sounds;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EfeitosSonoros  extends JPanel {

	public EfeitosSonoros() {
		setDoubleBuffered(true);
	}
	
	public void tocarFase() {
		try {
			URL soundFile = getClass().getResource("TrilhaSonora.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(sound);
			FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        volumeControl.setValue(-20.0f);
			clip.start();
		} catch (Exception e) {
			JOptionPane.showInputDialog(this, e);
		}
	}
	
	public void tocarTiro() {
		try {
			URL soundFile = getClass().getResource("SomTiro.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(sound);
			FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        volumeControl.setValue(-20.0f);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void tocarSomExplosao() {
		try {
			URL soundFile = getClass().getResource("SomExplosao.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(sound);
			FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        volumeControl.setValue(-20.0f);
			clip.start();
		} catch (Exception e) {
			JOptionPane.showInputDialog(this, e);
		}
	}
}
