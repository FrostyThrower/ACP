import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class Dispatcher {

    public void main(){

        Hashtable<String, String> prop = new Hashtable<String, String>();

        prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        prop.put("queue.richieste", "richieste");


        try {
            Context jndiContext = new InitialContext(prop);

            // ConnFact
            QueueConnectionFactory connFact = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            // Destionation (queue)
            Queue queue = (Queue) jndiContext.lookup("richieste");

            // QueueConn
            QueueConnection queueConn = connFact.createQueueConnection();
            queueConn.start();
            // Sess
            QueueSession queueSess = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueReceiver receiver = queueSess.createReceiver(queue);
            
            receiver.setMessageListener(new DispatcherListener(queueConn));

            System.out.println("[Dispatcher] On...");

            Thread.sleep(30000);

            receiver.close();
            queueSess.close();
            queueConn.close();

        } catch (NamingException | JMSException | InterruptedException e) {
            e.printStackTrace();
        }


    }


}
