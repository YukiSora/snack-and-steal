import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioEngine{
	// Variables
	private static AudioEngine audioEngine;
	HashMap <String, Audio> audios;
	
    // Constructor
	private AudioEngine(){
		audios = new HashMap<String, Audio>();   
    }
	
	public static AudioEngine getInstance(){
		if (audioEngine == null) {
			audioEngine = new AudioEngine();
        }

        return audioEngine;
	}
	
	// Methods
	public void addAudio(String name, Audio audio){
		audios.put(name, audio);
	}
	
	public void removeAudio(Audio audio){
		audios.remove(audio);
	}
	
	public void removeAudio(String name){
		audios.remove(name);
	}
	
	public void play(String name){
		audios.get(name).play();
	}
	
	public void loop(String name){
		audios.get(name).loop();
	}
	
	public void stop(String name){
		audios.get(name).stop();
	}
	
	public void close(String name){
		audios.get(name).close();
	}
}
