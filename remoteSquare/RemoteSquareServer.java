import java.io.*;
import java.net.*;

public class RemoteSquareServer {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Utilizzo: java RemoteSquareServer <porta>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server avviato sulla porta " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Connessione accettata da: " + clientSocket.getRemoteSocketAddress());
                    String inputLine;
                    
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.equalsIgnoreCase("fine")) {
                            System.out.println("Chiusura della connessione richiesta dal client.");
                            break;
                        }

                        try {
                            int number = Integer.parseInt(inputLine);
                            int squared = number * number;
                            out.println(squared);
                            System.out.println("Numero ricevuto: " + number + ", Quadrato calcolato: " + squared);
                        } catch (NumberFormatException e) {
                            out.println("Errore: input non valido. Inserire un numero intero.");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Errore di comunicazione con il client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Errore di apertura del server socket: " + e.getMessage());
        }
    }
}
