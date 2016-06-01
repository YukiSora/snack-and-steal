import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio implements Runnable{
    private Clip clip;
    private Thread thread;

    Audio(File file) throws IOException {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
        } catch (LineUnavailableException | UnsupportedAudioFileException e) {
            close();
            throw new IOException("Unavailable audio");
        }
        thread = new Thread(this);
        thread.start();
    }

    public void play() {
    	clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void close() {
        try {
            clip.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
    	System.out.println("Playing Audio..");
        play();
    }
}
