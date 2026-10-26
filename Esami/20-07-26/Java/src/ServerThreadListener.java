import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ServerThreadListener implements MessageListener {

    @Override
    public void onMessage(Message message) {

        TextMessage msg = (TextMessage) message;
  
        try {
            System.out.println("[" + Thread.currentThread().getName()+ "] " + msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
