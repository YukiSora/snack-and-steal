import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Driver {
	public static void main(String[] args) {
		AudioEngine a1 = AudioEngine.getInstance();

		try {
			a1.addAudio("Your Mother", new Audio(new File("poi.wav")));
			
			while (a1 != null) {
				a1.play("Your Mother");
			}

			System.out.println("Bad shit");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
