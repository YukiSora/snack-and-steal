import java.net.Socket;
import java.net.ServerSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server implements Runnable {
    private int port;
    private CopyOnWriteArrayList<Client> clients;
    private ExecutorService pool;

    Server(int port) {
        this.port = port;
        clients = new CopyOnWriteArrayList<>();
        pool = Executors.newFixedThreadPool(50);
        new Thread(this).start();
    }

    public static void main(String[] args) {
        Server server = new Server(2333);
    }

    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(port)) {
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

    private class Client {
        private Socket client;
        private ObjectInputStream in;
        private ObjectOutputStream out;

        Client(Socket client) throws IOException {
            this.client = client;
            in = new ObjectInputStream(client.getInputStream());
            out = new ObjectOutputStream(client.getOutputStream());
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

    private class ClientThread implements Callable<Void> {
        private Client client;

        ClientThread(Client client) {
            this.client = client;
        }

        @Override
        public Void call() {
            try {
                while (true) {
                    Data data = (Data)client.getIn().readObject();
                    System.out.println(data);
                    Iterator it = clients.iterator();
                    while (it.hasNext()) {
                        Client client = (Client)it.next();
                        if (!client.getSocket().equals(this.client.getSocket())) {
                            client.getOut().writeObject(data);
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
