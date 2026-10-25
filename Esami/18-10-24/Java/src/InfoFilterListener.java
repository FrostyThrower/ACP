import java.io.FileWriter;
import java.io.IOException;

import javax.jms.*;

public class InfoFilterListener implements MessageListener{

    @Override
    public void onMessage(Message message) {

        MapMessage msg = (MapMessage) message;

        try {
            String messageLog = msg.getString("messageLog");
            int tipo = Integer.parseInt(msg.getString("tipo"));

            System.out.println("[InfoFilter] Ricevuto: " + messageLog + " - " + String.valueOf(tipo).toString());

            if (tipo == 1){

                try (FileWriter writer = new FileWriter("info.txt", true)){
                    writer.write(messageLog + "\n");
                    System.out.println("[InfoFilter] Ricevuto e salvato error:" + messageLog);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
