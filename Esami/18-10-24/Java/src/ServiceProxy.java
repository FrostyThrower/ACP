import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceProxy implements ILogging{

    @Override 
    public void log(String messaggioLog, int tipo){

        try {
            Socket soc = new Socket("localhost", 8080);

            PrintWriter writer = new PrintWriter(soc.getOutputStream(), true);
            String msg = messaggioLog + "#" + String.valueOf(tipo).toString() + "\n";
            writer.println(msg);

            writer.close();
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
