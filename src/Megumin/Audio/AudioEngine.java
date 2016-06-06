package Megumin.Audio;

import java.util.HashMap;
import javax.sound.sampled.Clip;

public class AudioEngine {
    // Variables
    private static AudioEngine audioEngine;
    HashMap<String, Audio> audios;

    // Constructor
    private AudioEngine() {
        audios = new HashMap<String, Audio>();
    }

    public static AudioEngine getInstance() {
        if (audioEngine == null) {
            audioEngine = new AudioEngine();
        }

        return audioEngine;
    }

    // Methods
    public void addAudio(String name, Audio audio) {
        audios.put(name, audio);
    }

    public void removeAudio(Audio audio) {
        audios.remove(audio);
    }

    public void removeAudio(String name) {
        audios.remove(name);
    }

    public void play(String name) {
        audios.get(name).play();
    }

    public void loop(String name, int times) {
        audios.get(name).loop(times);
    }

    public void stop(String name) {
        audios.get(name).stop();
    }

    public void close(String name) {
        audios.get(name).close();
    }
}
