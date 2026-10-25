import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Server {
    
    public static void main(String[] args) {
        
        int port = 8080;

        try {
            ServerSocket sock = new ServerSocket(port);

            System.out.println("Ascolto...");

            Socket conn = sock.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            PrintWriter writer = new PrintWriter(conn.getOutputStream(), true);

            System.out.println(reader.readLine());
            writer.println("Ciao clieeeent");

            conn.close();
            sock.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
