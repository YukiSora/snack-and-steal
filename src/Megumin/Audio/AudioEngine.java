package Megumin.Audio;

import java.util.HashMap;
import java.util.Map.Entry;
import javax.sound.sampled.Clip;

public class AudioEngine {
    // Variables
    private static AudioEngine audioEngine;
    HashMap<String, Audio> audios;
    HashMap<String, Boolean> status;

    // Constructor
    private AudioEngine() {
        audios = new HashMap<>();
        status = new HashMap<>();
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
        status.put(name, false);
    }

    public void removeAudio(String name) {
        audios.remove(name);
        status.remove(name);
    }

    public void play(String name) {
        audios.get(name).play();
        status.put(name, true);
    }

    public boolean isPlayed(String name) {
        return status.get(name);
    }

    public void loop(String name) {
        audios.get(name).loop(Clip.LOOP_CONTINUOUSLY);
        status.put(name, true);
    }

    public void loop(String name, int times) {
        audios.get(name).loop(times);
        status.put(name, true);
    }

    public void setVolume(String name, float percent){
        audios.get(name).setVolume(percent);
    }

    public void stop(String name) {
        audios.get(name).stop();
        status.put(name, false);
    }

    public void stopAll() {
        for(Entry<String, Audio> entry : audios.entrySet()) {
            entry.getValue().stop();
            status.put(entry.getKey(), false);
        }
    }
}
