package RemoteDGramStrlen;

import java.io.*;
import java.net.*;

public class RemoteDGramStrlenClient {
    public static void main(String[] args){
        if(args.length != 2){
            System.out.println("Utilizzo: java RemoteDGramStrlen nodoServer portaServer");
            System.exit(1);
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try(Socket socket = new Socket(hostname, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))){

            System.out.println("Connessione al server " + hostname + " sulla porta " + port);

            String userInput;

            while(true){
                System.out.println("Inserisci una stringa: ");
                userInput = stdIn.readLine();

                if(userInput.equalsIgnoreCase("fine")){
                    out.println("fine");
                    break;
                }

                out.println(userInput);

                String response = in.readLine();
                System.out.println("Risultato del server" + response);
            }
        } catch (UnknownHostException e){
            System.err.println("Host sconosciuto: " + hostname);
        } catch (IOException e){
            System.err.println("Errore di I/O: " +e.getMessage());
        }


    }
}
