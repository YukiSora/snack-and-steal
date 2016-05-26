import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements Runnable{
    private Clip clip;

    Sound(File file) throws IOException {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
        } catch (LineUnavailableException | UnsupportedAudioFileException e) {
            close();
            throw new IOException("Unavailable audio");
        }
    }

    @Override
    public void run() {
        play();
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

    public static void main(String[] args) {
        Sound sound = null;

        try {
            sound = new Sound(new File("poi.wav"));
        } catch (IOException e) {
            System.out.println(e);
        }
        if (sound == null) {
            System.out.println("Failed");
            System.exit(1);
        }

        new Thread(sound).start();
        sound.loop();
        java.util.Scanner input = new java.util.Scanner(System.in);
        while (sound != null) {
            String s = input.nextLine();
            if (s.equals("play")) {
                sound.play();
            }
            else if (s.equals("stop")) {
                sound.stop();
            }
            else if (s.equals("close")) {
                sound.close();
                sound = null;
            }
        }
    }
}
