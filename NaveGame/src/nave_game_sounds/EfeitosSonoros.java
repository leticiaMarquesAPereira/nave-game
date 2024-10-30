package nave_game_sounds;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EfeitosSonoros extends JPanel {

    private Clip clipFase;
    private Clip clipMenu;// Atributo para o som da fase

    public EfeitosSonoros() {
        setDoubleBuffered(true);
    }

    public void tocarFase() {
        try {
            URL soundFile = getClass().getResource("musicaMenu.wav");
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
            DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
            clipFase = (Clip) AudioSystem.getLine(info); // Armazena o clip
            clipFase.open(sound);
            FloatControl volumeControl = (FloatControl) clipFase.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-05.0f);
            clipFase.start();
            clipFase.loop(Clip.LOOP_CONTINUOUSLY); // Para tocar em loop
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void pararFase() {
        if (clipFase != null && clipFase.isRunning()) {
            clipFase.stop();  
            clipFase.close();  
        }
    }
    
    public void pararSomMenu() {
        if (clipMenu != null && clipMenu.isRunning()) {
        	clipMenu.stop(); 
        	clipMenu.close();  
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
            URL soundFile = getClass().getResource("SomDano.wav");
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
            DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(sound);
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-30.0f);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tocarSomDano() {
        try {
            URL soundFile = getClass().getResource("SomExplosao.wav");
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
            DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(sound);
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-30.0f);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void tocarSomMenu() {
        try {
            URL soundFile = getClass().getResource("TrilhaSonora.wav");
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
            DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
            clipMenu = (Clip) AudioSystem.getLine(info);
            clipMenu.open(sound);
            FloatControl volumeControl = (FloatControl) clipMenu.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-30.0f);
            clipMenu.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
