import java.io.FileWriter;
import java.io.IOException;

import javax.jms.*;

public class ErrorCheckerListener implements MessageListener{

    private String inputString;

    public ErrorCheckerListener(String inputString){
        this.inputString = inputString;
    }

    @Override
    public void onMessage(Message message) {

        MapMessage data = (MapMessage) message;

        try {
            String messageLog = data.getString("messageLog");
            
            if (messageLog.compareTo(inputString) == 0){

                try (FileWriter writer = new FileWriter("error.txt", true)) {
                    writer.write(messageLog + "\n");
                    System.out.println("[ErrorChecker] Ricevuto e salvato error: " + messageLog);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            


        } catch (JMSException e) {
            e.printStackTrace();
        }





    }

    

}
