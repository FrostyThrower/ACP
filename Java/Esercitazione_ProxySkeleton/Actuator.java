package Java.Esercitazione_ProxySkeleton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Actuator {

     public static void main(String[] args) {
        
        IDispatcher dispatcher = new DispatcherProxy();

        for(int i = 0; i < 15; i++) {
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int output = dispatcher.getCmd();
            String replyMessage;

            switch (output) {
                case 0:
                    replyMessage = "leggi";
                    break;
                
                case 1:
                    replyMessage = "scrivi";
                    break;
                case 2:
                    replyMessage = "configura";
                    break;
                case 3:
                    replyMessage = "reset";
                    break;
                default:
                    replyMessage = "error";
                    break;
            }

            System.out.println(replyMessage);

            Path path = Path.of("cmdlog.txt");
            try {
                Files.writeString(path, replyMessage +"\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

     }
}