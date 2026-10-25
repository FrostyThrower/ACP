import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class Receiver {

    public static void main(String[] args) {
        
        // Definizione proprietà JNDI per accesso a ActiveMQ
        Hashtable<String, String> prop = new Hashtable<String, String>();

        prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
        

        prop.put("queue.test", "testqueue");

        try {
            // Connessione al JNDI
            Context jndicontext = new InitialContext(prop);

            // Creo una connection
            QueueConnectionFactory queueConnFact = (QueueConnectionFactory) jndicontext.lookup("QueueConnectionFactory");
            // Credo una Destionation di tipo Queue
            Queue queue = (Queue) jndicontext.lookup("test");
            
            // Creo una connection alla Queue
            QueueConnection queueConn = (QueueConnection) queueConnFact.createQueueConnection();
            queueConn.start();

            // Creo la session
            QueueSession ses = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueReceiver receiver = ses.createReceiver(queue);
            receiver.setMessageListener(new MyListener());

            System.out.println("Attendo...");

            try {
                Thread.sleep(30000);
            } catch (InterruptedException e ){
                e.printStackTrace();
            }


            receiver.close();
            ses.close();
            queueConn.close();
            
    
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }

    }

}
