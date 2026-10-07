package Java.Esercizio_Networking.Ese_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) {
        
        try {
            Socket soc = new Socket("localhost", 8050);   

            BufferedReader reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            PrintWriter writer = new PrintWriter(soc.getOutputStream(), true);

            writer.println("Hello server!");
            String risposta = reader.readLine();

            System.out.println("[Client] Messaggio: " + risposta);

            reader.close();
            writer.close();
            soc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
