package Java.Esercizio_Networking.Ese_1;
import java.net.*;


public class Server {
    
    public static void main(String[] args) {
        
        try{

            ServerSocket server = new ServerSocket(8050, 50);
            System.err.println("[Server] Listening on port 8050...");

            while(true){

                Socket soc = server.accept();
                System.err.println("[Server] Client accepted");


                Thread t = new Thread(new Worker(soc));
                t.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
