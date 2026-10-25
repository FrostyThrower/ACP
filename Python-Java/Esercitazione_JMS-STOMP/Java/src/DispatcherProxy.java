import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DispatcherProxy implements IServer{

    public String deposita(int id_articolo){

        Socket soc = null;
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            soc = new Socket("localhost", 8080);
            
            reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            writer = new PrintWriter(soc.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String msg = "deposita#" + String.valueOf(id_articolo).toString();
        writer.println(msg);
        
        String response = null;
        try {
            response = reader.readLine();
            System.out.println(response);
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public int preleva(){

        Socket soc = null;
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            soc = new Socket("localhost", 8080);
            
            reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            writer = new PrintWriter(soc.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int id_return = -1;

        writer.println("preleva");

        try {
            String response = reader.readLine();
            id_return = Integer.parseInt(response);
            System.out.println(response);
            
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return id_return;
    }
}
