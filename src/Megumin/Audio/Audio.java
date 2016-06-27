package Megumin.Audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio implements Runnable{
    private Clip clip;
    private Thread thread;

    public Audio(String filename) throws IOException {
        this(new File(filename));
    }

    public Audio(File file) throws IOException {
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
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void setVolume(float percent){
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float max = gainControl.getMaximum();
        float min = gainControl.getMinimum();
        gainControl.setValue(min + (max - min) * percent);
    }

    public void loop(int times) {
        clip.loop(times);
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
    }
}
