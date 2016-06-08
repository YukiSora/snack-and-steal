import java.net.Socket;
import java.net.UnknownHostException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.util.Scanner;

public class Client {
    private int port;
    private String ip;

    Client(String ip, int port) throws InterruptedException, UnknownHostException, IOException {
        this.ip = ip;
        this.port = port;

        Socket client = new Socket(ip, port);
        Thread clientThread = new Thread(new OutputThread(new ObjectOutputStream(client.getOutputStream())));
        clientThread.start();
        clientThread.join();
        Thread serverThread = new Thread(new InputThread(new ObjectInputStream(client.getInputStream())));
        serverThread.start();
        serverThread.join();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 2333);
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private class InputThread implements Runnable {
        private ObjectInputStream in;

        InputThread(ObjectInputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println((Data)in.readObject());
                }
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    private class OutputThread implements Runnable {
        private ObjectOutputStream out;

        OutputThread(ObjectOutputStream out) {
            this.out = out;
        }

        @Override
        public void run() {
            Scanner input = new Scanner(System.in);

            try {
                while (true) {
                    String s = input.nextLine();
                    out.writeObject(new Data(s));
                    out.flush();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
