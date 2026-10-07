package Java.Esercizio_Networking.Ese_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Worker implements Runnable{

    private Socket soc;

    public Worker(Socket soc){
        this.soc = soc;
    }

    public void run(){

        try {
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
            PrintWriter writer = new PrintWriter(this.soc.getOutputStream(), true);

            String risposta = reader.readLine();
            System.out.println(Thread.currentThread().getName() + " - Messaggio: " + risposta);
            
            writer.println("richiesta ricevuta");

            reader.close();
            writer.close();
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }
    
}
