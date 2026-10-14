package serverPackage;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class Server2 {
    static void main(String[] args) throws IOException{
        //1.créer le serveur
        ServerSocket socketServer = new ServerSocket(1234);

        //2.attendre la connexion du client
        Socket socket = socketServer.accept();
        System.out.println("un client est connecté");

        //3. recuperer le flux d'entree du socket
        InputStream is = socket.getInputStream();
        //4. creer un flux pour recevoir un entier
        DataInputStream dis = new DataInputStream(is);
        //5. recevoir le nombre envoye par le client
        int nb = dis.readInt();
        System.out.println("le serveur a recu le nombre "+nb);

        //6. multiplier le nombre par 5
        int x = nb * 5;
        System.out.println("Calcul du serveur : "+nb+" * 5 = "+x);

        //7. recuperer le flux de sortie du socket
        OutputStream os = socket.getOutputStream();
        //8. creer un flux pour envoyer un entier
        DataOutputStream dos = new DataOutputStream(os);
        //9. envoyer le resultat au client
        dos.writeInt(x);
        System.out.println("le résultat a été envoyé au client."+nb);

        //10. fermer les flux de communication et le socket du client et le serveur
        dis.close();
        dos.close();
        socket.close();
        socketServer.close();
    }
}
