import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Driver {
	public static void main(String[] args) {
		AudioEngine a1 = AudioEngine.getInstance();

		try {
			a1.addAudio("Your Mother", new Audio(new File("poi.wav")));
			a1.addAudio("Main Theme", new Audio(new File("main_theme.wav")));
			a1.addAudio("Animals Attacked", new Audio(new File("animals_attacked.wav")));
			
			a1.play("Your Mother");
			new java.util.Scanner(System.in).nextInt();
			a1.stop("Your Mother");
			
			
			a1.play("Main Theme");
			new java.util.Scanner(System.in).nextInt();
			a1.stop("Main Theme");
			
			System.out.println("End Music");
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
