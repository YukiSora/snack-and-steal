import java.net.Socket;
import java.net.UnknownHostException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.util.Scanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Client <T> {
    private int port;
    private String ip;

    Client(String ip, int port) throws InterruptedException, UnknownHostException, IOException {
        this.ip = ip;
        this.port = port;

        Socket client = new Socket(ip, port);
        Thread clientThread = new Thread(new OutputThread <T>(new ObjectOutputStream(client.getOutputStream()), Data.class));
        clientThread.start();
        clientThread.join();
        Thread serverThread = new Thread(new InputThread <T>(new ObjectInputStream(client.getInputStream())));
        serverThread.start();
        serverThread.join();
    }

    public static void main(String[] args) {
        try {
            Client <Data> client = new Client <Data> ("localhost", 2333);
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (UnknownHostException e) {
            System.out.println(e);
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
                    System.out.println((T)in.readObject());
                }
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    private class OutputThread <T> implements Runnable {
        private ObjectOutputStream out;
        private Class cls;

        OutputThread(ObjectOutputStream out, Class cls) {
            this.out = out;
            this.cls = cls;
        }

        @Override
        public void run() {
            Scanner input = new Scanner(System.in);

            try {
                while (true) {
                    String s = input.nextLine();
                    Constructor constructor = cls.getDeclaredConstructor(String.class);
                    constructor.setAccessible(true);

                    Object obj = (T) constructor.newInstance(s);
                    T t = (T) obj;

                    out.writeObject(t);
                    out.flush();
                }
            } catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                System.out.println(e);
            }
        }
    }
}
