package BluebellAdventures;

import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.Clip;

import Megumin.Audio.Audio;
import Megumin.Audio.AudioEngine;

public class AudioDriver {
	public static void main(String[] args) {
		AudioEngine audioEngine = AudioEngine.getInstance();

		try {
			audioEngine.addAudio("eating", new Audio("resource/audio/eating.wav"));
			audioEngine.addAudio("attacking", new Audio("resource/audio/attacking.wav"));
			audioEngine.addAudio("slurping", new Audio("resource/audio/slurping.wav"));
			audioEngine.addAudio("walking", new Audio("resource/audio/walking.wav"));
			audioEngine.addAudio("menu", new Audio("resource/audio/menu.wav"));
			audioEngine.addAudio("main", new Audio("resource/audio/main.wav"));
			audioEngine.addAudio("nervous", new Audio("resource/audio/nervous.wav"));
			audioEngine.addAudio("victory", new Audio("resource/audio/victory.wav"));
			audioEngine.addAudio("cupboard", new Audio("resource/audio/cupboard.wav"));
			audioEngine.addAudio("door", new Audio("resource/audio/door.wav"));
			audioEngine.addAudio("fridge", new Audio("resource/audio/fridge.wav"));
			audioEngine.addAudio("unlock", new Audio("resource/audio/unlock.wav"));

		} catch (IOException e) {
			System.out.println(e);
		}

		audioEngine.loop("menu", Clip.LOOP_CONTINUOUSLY);
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.print("Please enter audio name: ");
			String audio = in.nextLine();
			System.out.println("Please enter action(0: stop, 1: play): ");
			int action = in.nextInt();
			in.nextLine();
			if (action == 0) {
				audioEngine.stop(audio);
			}
			else if (action == 1) {
				audioEngine.play(audio);
			}
		}
	}
}
