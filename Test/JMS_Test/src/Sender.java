import java.util.Hashtable;
import javax.jms.*;
import javax.naming.*;

public class Sender {


    public static void main(String[] args) {

        Hashtable<String, String> prop = new Hashtable<String, String>();

        prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
        

        prop.put("queue.test", "testqueue");


        try {

            Context jndicontext = new InitialContext(prop);

            // ConnFactory
            QueueConnectionFactory queueConnFact = (QueueConnectionFactory) jndicontext.lookup("QueueConnectionFactory");
            // Destionation - Queue
            Queue queue = (Queue) jndicontext.lookup("test");
            // QueueConn
            QueueConnection queueConn = queueConnFact.createQueueConnection();
            // Session
            QueueSession queueSess = (QueueSession) queueConn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Sender
            QueueSender sender = queueSess.createSender(queue);

            TextMessage msg = queueSess.createTextMessage();

            msg.setText("FORZA NAPOLI SEMPRE");
            sender.send(msg);

            sender.close();
            queueSess.close();
            queueConn.close();

        } catch (NamingException | JMSException e){
            e.printStackTrace();
        }


    }

}
