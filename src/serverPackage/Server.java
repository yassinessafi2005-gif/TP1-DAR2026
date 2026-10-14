package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //1.créer le serveur
        ServerSocket socketServer = new ServerSocket(1234);

        System.out.println("Je suis un serveur en attente la connexion d'un client");

        //2.attendre la connexion du client
        Socket socket = socketServer.accept();

        System.out.println("Un client est connecte");

        //3.fermer les sockets
        socket.close();
        socketServer.close();
    }
}
