package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client3 {
    public static void main(String[] args) throws IOException {

        //1. definir l'adresse IP reelle du serveur
        String ipServeur = "192.168.1.48";

        //2. recuperer l'adresse du serveur
        InetAddress adresse = InetAddress.getByName(ipServeur);
        //3. definir l'adresse de destination et le port 1234
        InetSocketAddress destination = new InetSocketAddress(adresse,1234);

        //4. creer le socket client
        Socket socket = new Socket();
        //5. etablir la connexion avec le serveur
        socket.connect(destination);
        System.out.println("je suis un client connecté avec le serveur");

        //6. creer le flux de sortie pour envoyer les donnees
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        //7. creer le flux d'entree pour recevoir les reponses
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        //8. creer un Scanner pour lire les choix et les nombres
        Scanner scanner = new Scanner(System.in);

        //9. afficher le menu jusqu'a ce que le client quitte
        while (true) {
            //10. afficher les operations disponibles
            System.out.println(" CALCULATRICE \n");
            System.out.println("1. Addition");
            System.out.println("2. Soustraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            //11. lire le choix de l'utilisateur
            int choix = scanner.nextInt();
            //12. envoyer le choix au serveur
            dos.writeInt(choix);

            //13. quitter si le choix est 0
            if (choix == 0) {
                break;
            }
            //14. verifier si le choix est valide
            if (choix < 1 || choix > 4) {
                System.out.println("Choix invalide !");
                continue;
            }

            //15. demander et lire le premier nombre
            System.out.print("Donner le premier nombre : ");
            double x = scanner.nextDouble();
            //16. demander et lire le deuxieme nombre
            System.out.print("Donner le deuxieme nombre : ");
            double y = scanner.nextDouble();

            //17. envoyer les deux nombres au serveur
            dos.writeDouble(x);
            dos.writeDouble(y);
            dos.flush();

            //18. recevoir la reponse du serveur
            String reponse = dis.readUTF();
            //19. afficher le resultat recu
            System.out.println(reponse);
        }

        //20. fermer les flux de communication et le socket et le Scanner
        dis.close();
        dos.close();
        scanner.close();
        socket.close();
    }
}