import java.net.*;
import java.io.*;

public class Client{

    public static void main(String args[]) throws IOException, ClassNotFoundException{
    	Socket client = new Socket( "localhost", 4000 );
    	
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream  ois = new ObjectInputStream( client.getInputStream());
        
        new Thread(new InputThread(oos, ois)).start();
    }
    
    static private class InputThread implements Runnable {
    	private OutputStream out;
        private InputStream in;

        InputThread(OutputStream out, InputStream in) {
        	this.out = out;
            this.in = in;
        }

        @Override
        public void run() {
			try {
				Weapon to = new Weapon( 1, "Candlestick", 2.5);

                System.out.println(to);

                ((ObjectOutputStream) out).writeObject(to);
                out.flush();
                Object received = ((ObjectInputStream) in).readObject();
          
                System.out.println(received);

                out.close();
                in.close();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
        }
    }
}