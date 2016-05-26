import java.net.Socket;
import java.net.ServerSocket;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.Vector;
import java.util.Enumeration;

public class Server {
    static private Vector<Socket> clients = new Vector<Socket>(4);

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(50);

        try (ServerSocket server = new ServerSocket(55494)) {
            while (true) {
                try {
                    Socket client = server.accept();
                    clients.add(client);
                    Callable<Void> task = new ClientThread(client);
                    pool.submit(task);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static private class ClientThread implements Callable<Void> {
        private Socket client;

        ClientThread(Socket client) {
            this.client = client;
        }

        @Override
        public Void call() {
            try {
                InputStream in = client.getInputStream();
                int c;
                while ((c = in.read()) != -1) {
                    Enumeration clients = Server.clients.elements();
                    while (clients.hasMoreElements()) {
                        Socket client = (Socket)clients.nextElement();
                        if (!client.equals(this.client)) {
                            OutputStream out = client.getOutputStream();
                            out.write(c);
                            System.out.write(c);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                clients.remove(this);
                try {
                    client.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }

            return null;
        }
    }
}
