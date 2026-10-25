import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class ErrorChecker {

    public void main(String[] args){

        Hashtable<String, String> prop = new Hashtable<String, String>();

        prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        prop.put("queue.error", "error");
    
        try {
            
            Context jndiContext = new InitialContext(prop);

            QueueConnectionFactory queueConnFact = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            
            Queue queue = (Queue) jndiContext.lookup("error");

            QueueConnection queueConn = queueConnFact.createQueueConnection();
            queueConn.start();
            
            QueueSession sess = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueReceiver receiver = sess.createReceiver(queue);
            receiver.setMessageListener(new ErrorCheckerListener(args[0]));

            System.out.println("[ErrorChecker] ErrorChecker on...");

            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            receiver.close();
            sess.close();
            queueConn.close();

        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }


    
    }


}
