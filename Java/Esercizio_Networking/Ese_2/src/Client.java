import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        
        try {

            InetAddress addr = InetAddress.getLocalHost();

            Socket sock = new Socket(addr, 8080);

            BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);

            writer.println("Ciao serveeer");
            System.out.println(reader.readLine());

            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
