import java.io.*;
import java.net.*;

public class RemoteSquareClient {

    public static void main(String[] args) {
        if (args.length != 2) {     //due argomenti devono essere presenti quando viene invocato il Client, se sono presenti in numero di verso fai qeusto:
            System.out.println("Utilizzo: java RemoteSquareClient <hostname> <porta>");
            System.exit(1);
        }

        String hostname = args[0];      //alla Stringa hostname assegno il valore passato come priimo parametro args[0]
        int port = Integer.parseInt(args[1]);   //all'intero port assegno il valore passato come secondo parametro args[1]

        try (Socket socket = new Socket(hostname, port);    //creo il nuovo oggetto di tipo Socket chiamato "socket"
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connessione al server " + hostname + " sulla porta " + port);

            String userInput;
            while (true) {      //loop
                System.out.print("Inserisci un numero intero (o 'fine' per terminare): ");
                userInput = stdIn.readLine();

                if (userInput.equalsIgnoreCase("fine")) {   //esce se trova la parola "fine"
                    out.println("fine");
                    break;
                }

                out.println(userInput); //fa "uscire" la stringa passata da terminale

                //---------------------------------------------------------------------//

                String response = in.readLine();
                System.out.println("Risultato dal server: " + response);
            }

        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto: " + hostname);
        } catch (IOException e) {
            System.err.println("Errore di I/O: " + e.getMessage());
        }
    }
}
