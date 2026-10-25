import javax.jms.*;

public class MyListener implements MessageListener{

    public void onMessage(Message msg){
        TextMessage textmsg = (TextMessage) msg;
        try {
            System.out.println(textmsg.getText());
        } catch (JMSException e){
            e.printStackTrace();
        }
    }
}
