import java.util.Hashtable;
import javax.jms.*;
import javax.naming.*;

public class LogThread extends Thread{

    private String messaggioLog;
    private int tipo;

    public LogThread(String messaggioLog, int tipo){
        this.messaggioLog = messaggioLog;
        this.tipo = tipo;
    }

    @Override 
    public void run(){

        Hashtable<String, String> prop = new Hashtable<String, String>();

        prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        prop.put("queue.error", "error");
        prop.put("queue.info", "info");

        try {
            Context jndiContext = new InitialContext(prop);

            // ConnFact
            QueueConnectionFactory queueConnFactory = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");

            if (tipo == 2){

                Queue queue_error = (Queue) jndiContext.lookup("error");
                QueueConnection queueConn_error = queueConnFactory.createQueueConnection();
                QueueSession sess_error = queueConn_error.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                QueueSender sender_error = sess_error.createSender(queue_error);

                MapMessage msg = sess_error.createMapMessage();
                msg.setString("messageLog", messaggioLog);
                msg.setString("tipo", String.valueOf(tipo).toString());
                
                System.out.print("[LoggingServer] Inviato error: " + messaggioLog + " - " + String.valueOf(tipo).toString() + "\n");

                sender_error.send(msg);
                
                sender_error.close();
                sess_error.close();

            } else {
                
                Queue queue_info = (Queue) jndiContext.lookup("info");
                QueueConnection queueConn_info = queueConnFactory.createQueueConnection();

                QueueSession sess_info = queueConn_info.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                QueueSender sender_info = sess_info.createSender(queue_info);

                MapMessage msg = sess_info.createMapMessage();
                msg.setString("messageLog", messaggioLog);
                msg.setString("tipo", String.valueOf(tipo).toString());

                System.out.print("[LoggingServer] Inviato info: " + messaggioLog + " - " + String.valueOf(tipo).toString());

                sender_info.send(msg);

                sender_info.close();
                sess_info.close();

            }

        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

}
