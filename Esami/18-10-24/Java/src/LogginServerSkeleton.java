import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;;

public abstract class LogginServerSkeleton implements ILogging{

    public void run_skeleton(){
        try {
            ServerSocket soc = new ServerSocket(8080);

            while(true){
                Socket client = soc.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String msg = reader.readLine();
                String messaggioLog = msg.split("#")[0];
                int tipo = Integer.valueOf(msg.split("#")[1]);

                this.log(messaggioLog, tipo);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    


}
