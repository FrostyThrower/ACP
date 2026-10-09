import java.util.Hashtable;
import java.util.Random;

import javax.naming.*;
import javax.jms.*;


public class Client {
    

    public static void main(String[] args) {

        int NUM_DEPOSITO = 5;
        int NUM_PRELEVA = 5;
        
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
            Queue queue_to_send = (Queue) jndiContext.lookup("magazzino");
            Queue queue_to_receive = (Queue) jndiContext.lookup("client");

            
            // prendo admnistred object Connection
            QueueConnection queueConn = queueFactory.createQueueConnection();
            // Creo una sessione dalla connection
            QueueSession queueSessionSender = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueSession queueSessionReceiver = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            // Creo sender per magazzino_queue e receiver per client_queue
            QueueSender sender_magazzino = queueSessionSender.createSender(queue_to_send);
            QueueReceiver receiver_magazzino = queueSessionReceiver.createReceiver(queue_to_receive);
            
            ClientListener listener = new ClientListener();
            receiver_magazzino.setMessageListener(listener);

            queueConn.start();

            MapMessage messageDeposito = queueSessionSender.createMapMessage();
            Random ran = new Random();

            // Richieste di tipo deposito
            for (int i = 0; i < NUM_DEPOSITO; i++) {
                messageDeposito.setString("operazione", "deposita");
                messageDeposito.setInt("valore", ran.nextInt(100)+1);
                sender_magazzino.send(messageDeposito);
                System.out.println("[Client] Inviata richiesta " + messageDeposito.getInt("valore"));
            }

            // Richieste di tipo preleva
            MapMessage messagePreleva = queueSessionSender.createMapMessage();

            for (int i = 0; i < NUM_PRELEVA; i++) {
                messagePreleva.setString("operazione", "preleva");
                sender_magazzino.send(messagePreleva);
                System.out.println("[Client] Inviata preleva");
            }


            System.out.println("Premi INVIO per terminare...");
            new java.util.Scanner(System.in).nextLine();
            queueConn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
