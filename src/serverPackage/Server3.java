package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 {
    public static void main(String[] args) throws IOException {
        //1. recuperer l'adresse IP de la machine serveur
        InetAddress adresse = InetAddress.getLocalHost();

        //2. creer le socket serveur
        ServerSocket socketServer = new ServerSocket();
        //3. associer le serveur a son adresse IP et au port 1234
        socketServer.bind(new InetSocketAddress(adresse, 1234));

        //4. afficher l'adresse IP du serveur
        System.out.println("Adresse IP : " + adresse.getHostAddress());

        //5. attendre la connexion d'un client
        Socket socket = socketServer.accept();
        //6. afficher l'adresse IP du client connecte
        System.out.println("Client connecte : " + socket.getInetAddress().getHostAddress());

        //7. creer le flux d'entree pour recevoir les donnees
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        //8. creer le flux de sortie pour envoyer les resultats
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        //9. repeter les operations jusqu'a ce que le client choisisse l'operation 0
        while (true) {
            //10. recevoir le choix de l'operation
            int operation = dis.readInt();

            //11. quitter si le client choisit 0
            if (operation == 0) {
                break;
            }

            //12. recevoir les deux nombres envoyes par le client
            double x = dis.readDouble();
            double y = dis.readDouble();
            //13. initialiser le resultat et la reponse
            double resultat = 0;
            String reponse;

            //14. effectuer le calcul selon l'operation choisie
            switch (operation) {
                //Addition
                case 1:
                    resultat = x + y;
                    reponse = "Resultat : " + resultat;
                    break;
                //Soustraction
                case 2:
                    resultat = x - y;
                    reponse = "Resultat : " + resultat;
                    break;
                //Multiplication
                case 3:
                    resultat = x * y;
                    reponse = "Resultat : " + resultat;
                    break;
                //Division
                case 4:
                    if (y == 0) {
                        reponse = "Erreur : division par zero !";
                    } else {
                        resultat = x / y;
                        reponse = "Resultat : " + resultat;
                    }
                    break;
                //choix invalide
                default:
                    reponse = "Operation invalide !";
            }
            //15. envoyer la reponse au client
            dos.writeUTF(reponse);
            dos.flush();

            //16. afficher le resultat dans la console du serveur
            System.out.println(reponse);
        }

        // 17. Fermer les flux de communication et le socket client et le socket serveur
        dis.close();
        dos.close();
        socket.close();
        socketServer.close();
    }
}
