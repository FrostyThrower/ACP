import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ServerThreadListener implements MessageListener {

    private String type;

    public ServerThreadListener(String type){
        this.type = type;
    }

    @Override
    public void onMessage(Message message) {

        TextMessage msg = (TextMessage) message;
  
        try {
            System.out.println("[Listener-" + type + "] " + msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
