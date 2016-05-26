import java.net.Socket;
import java.net.UnknownHostException;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket client = new Socket("115.28.241.237", 2334)) {
            new Thread(new InputThread(client.getInputStream())).start();

            OutputStream out = client.getOutputStream();
            Scanner input = new Scanner(System.in);
            while (true) {
                String s = input.nextLine() + "\r\n";
                for (int i = 0; i < s.length(); i++) {
                    out.write(s.charAt(i));
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Unknow Host");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static private class InputThread implements Runnable {
        private InputStream in;

        InputThread(InputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int c;
                    while ((c = in.read()) != -1) {
                        System.out.write(c);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
