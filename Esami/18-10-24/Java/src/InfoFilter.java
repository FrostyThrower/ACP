import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class InfoFilter {

    public void main(String[] args){

        Hashtable<String, String> prop = new Hashtable<String, String>();

        prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        prop.put("queue.info", "info");

        try {

            Context jndiContext = new InitialContext(prop);

            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            Queue queue = (Queue) jndiContext.lookup("info");

            QueueConnection queueConn = queueConnectionFactory.createQueueConnection();
            queueConn.start();

            QueueSession queueSess = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueReceiver receiver = queueSess.createReceiver(queue);
            receiver.setMessageListener(new InfoFilterListener());

            System.out.println("[InfoFilter] InfoFilter on...");


            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            receiver.close();
            queueSess.close();
            queueConn.close();
        
            
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }

    }

}
