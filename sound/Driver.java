import java.io.File;
import java.util.HashMap;

public class Driver{
	public static void main(String[] args){
		AudioEngine a1 = new AudioEngine();
		
		a1.addAudio("Your Mother", new File("poi.wav"));
	}	
}
