import java.util.Hashtable;

import javax.jms.*;
import javax.management.JMException;
import javax.naming.*;
import javax.sound.midi.Receiver;

public class Dispatcher {

    public static void main(){
        
        Hashtable<String, String> prop = new Hashtable<String, String>();

        prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        prop.put("queue.take", "request");
        
        try {
            
            Context jndiContext = new InitialContext(prop);

            // ConnFactory
            QueueConnectionFactory connFactory = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            // Destination (queue)
            Queue queue = (Queue) jndiContext.lookup("take");
            
            // Queue
            QueueConnection queueConn = (QueueConnection) connFactory.createQueueConnection();
            queueConn.start();

            QueueSession ses = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueReceiver receiver = ses.createReceiver(queue);

            DispatcherListener listener = new DispatcherListener(queueConn);

            receiver.setMessageListener(listener);

            Thread.sleep(20000);

            receiver.close();
            ses.close();
            queueConn.close();

            
        } catch (NamingException | JMSException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
