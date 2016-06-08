import java.net.Socket;
import java.net.UnknownHostException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try (Socket client = new Socket("localhost", 2333)) {
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            new Thread(new InputThread(new ObjectInputStream(client.getInputStream()))).start();

            while (true) {
                String s = input.nextLine();
                out.writeObject(new Data(s));
                out.flush();
            }
        } catch (UnknownHostException e) {
            System.out.println("Unknow Host");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static private class InputThread implements Runnable {
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
}
