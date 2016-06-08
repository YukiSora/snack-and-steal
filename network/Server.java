import java.net.Socket;
import java.net.ServerSocket;


import java.io.InputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    static private CopyOnWriteArrayList<Client> clients;

    public static void main(String[] args) {
        clients = new CopyOnWriteArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(50);

        try (ServerSocket server = new ServerSocket(2333)) {
            while (true) {
                try {
                    Client client = new Client(server.accept());
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

    static private class Client {
        private Socket client;
        private ObjectInputStream in;
        private ObjectOutputStream out;

        Client(Socket client) {
            this.client = client;
            try {
                in = new ObjectInputStream(client.getInputStream());
                out = new ObjectOutputStream(client.getOutputStream());
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        public Socket getSocket() {
            return client;
        }

        public ObjectInputStream getIn() {
            return in;
        }

        public ObjectOutputStream getOut() {
            return out;
        }
    }

    static private class ClientThread implements Callable<Void> {
        private Client client;

        ClientThread(Client client) {
            this.client = client;
        }

        @Override
        public Void call() {
            try {
                while (true) {
                    Data data = (Data)client.getIn().readObject();
                    Iterator it = clients.iterator();
                    while (it.hasNext()) {
                        Client client = (Client)it.next();
                        if (!client.getSocket().equals(this.client.getSocket())) {
                            client.getOut().writeObject(data);
                            System.out.println(data);
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                clients.remove(this);
                try {
                    client.getSocket().close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }

            return null;
        }
    }
}
