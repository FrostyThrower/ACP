import java.util.Hashtable;
import java.util.Random;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;


public class Magazzino{


    public static void main(String[] args){


        Hashtable<String, String> properties = new Hashtable<>();
        properties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        properties.put("queue.magazzino", "clientToMagazzino"); // queue in cui invia
        properties.put("queue.client", "magazzinoToClient"); // queue in cui riceve

        try {
            // mi collego al JNDI di ActiveMq
            Context jndiContext = new InitialContext(properties);

            // prendo admnistred object Factory
            QueueConnectionFactory queueFactory = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            
            // Prendo l'oggetto queue con operazione di lookup
            Queue queue_to_send = (Queue) jndiContext.lookup("client");
            Queue queue_to_receive = (Queue) jndiContext.lookup("magazzino");

            
            // prendo admnistred object Connection
            QueueConnection queueConn = queueFactory.createQueueConnection();
            // Creo una sessione dalla connection
            QueueSession queueSessionReceiver = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            
            QueueReceiver receiver = queueSessionReceiver.createReceiver(queue_to_receive);

            Coda coda = new Coda(10);

            MagazzinoListener listener = new MagazzinoListener(queue_to_send, queueConn, coda);
            receiver.setMessageListener(listener);

            queueConn.start();

            System.out.println("Premi INVIO per terminare...");
            new java.util.Scanner(System.in).nextLine();
            queueConn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}