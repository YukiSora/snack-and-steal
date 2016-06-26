package Megumin.Network;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Client <T> {
    private ObjectOutputStream out;

    public Client(String ip, int port) throws InterruptedException, UnknownHostException, IOException {
        Socket client = new Socket(ip, port);

        out = new ObjectOutputStream(client.getOutputStream());
        new Thread(new InputThread <T>(new ObjectInputStream(client.getInputStream()))).start();
    }

    public void send(T data) {
        try {
            out.writeObject(data);
            out.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private class InputThread <T> implements Runnable {
        private ObjectInputStream in;

        InputThread(ObjectInputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(in.readObject());
                }
            } catch (ClassNotFoundException | IOException e) {
                System.out.println(e);
            }
        }
    }
}
