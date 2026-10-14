package clientPackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    static void main(String[] args) throws IOException {
        //1. afficher un message avant la connexion
        System.out.println("je suis un client pas encore connecté...");
        //2. se connecter au serveur avec son adresse IP et son port
        Socket socket = new Socket("192.168.1.48",1234);
        System.out.println("je suis un client connecté");

        //3. creer un objet Scanner pour saisir un entier au clavier
        Scanner scanner = new Scanner(System.in);
        //4. demander a l'utilisateur de saisir un entier
        System.out.println("ecrire un entier x : ");
        int x = scanner.nextInt();

        //5. recuperer le flux de sortie du socket
        OutputStream os = socket.getOutputStream();
        //6. creer un flux pour envoyer un entier
        DataOutputStream dos = new DataOutputStream(os);
        //7. envoyer le nombre au serveur
        dos.writeInt(x);
        System.out.println("Nombre envoyé au serveur : "+x);

        //8. recuperer le flux d'entree du socket
        InputStream is = socket.getInputStream();
        //9. creer un flux pour recevoir un entier
        DataInputStream dis = new DataInputStream(is);
        //10. recevoir le resultat calcule par le serveur
        int xx = dis.readInt();
        System.out.println("Result recu du server : "+xx);

        //11. fermer les flux de communication et le socket et le Scanner
        dis.close();
        dos.close();
        socket.close();
        scanner.close();
    }
}
