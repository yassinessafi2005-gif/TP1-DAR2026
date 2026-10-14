package clientPackage;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        //1.créer le socket client
        System.out.println("Je suis un client pas encore connecte");
        Socket socket = new Socket("192.168.1.48", 1234);

        //2.connexion établie
        System.out.println("Je suis un client connecte");

        //3.fermer le socket
        socket.close();
    }
}