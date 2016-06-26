package BluebellAdventures;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import Megumin.Network.Client;

public class ClientDriver {
    public static void main(String[] args) {
        try {
            Client<String> client = new Client<String>("localhost", 2333);
            Scanner in = new Scanner(System.in);
            while (true) {
                client.send(in.nextLine());
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}