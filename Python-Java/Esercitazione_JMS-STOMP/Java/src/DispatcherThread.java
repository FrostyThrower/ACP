
import javax.jms.*;
import javax.management.JMException;

public class DispatcherThread extends Thread{

    private QueueConnection queueConn;
    private Queue queue_to_send;
    private String correlation_id;
    private String method;
    private int id_articolo;
    private final String WHO_I_AM = "[" + Thread.currentThread().getName() + "]"; 
    private final IServer server;
    private QueueSession ses;
    private QueueSender sender;

    public DispatcherThread(QueueConnection queueConn, String method, int id_articolo, Queue queue, String correlation_id){
        this.queueConn = queueConn;
        this.queue_to_send = queue;
        this.correlation_id = correlation_id;
        this.method = method;
        this.id_articolo = id_articolo;
        this.server = new DispatcherProxy();

        try {
            ses = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            sender = ses.createSender(queue_to_send);         
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    @Override 
    public void run(){

        System.out.println(WHO_I_AM + " request " + method);

        String response = null;

        if(method.compareTo("deposita") == 0){
            response = server.deposita(id_articolo);

        } else if (method.compareTo("preleva") == 0){
            // server.preleva() restituisce un int
            response = String.valueOf(server.preleva()).toString();

        } else {
            System.out.println(WHO_I_AM + "ERROR: Unknown Method");
            return;
        }

        try {
            TextMessage msg = ses.createTextMessage();
            msg.setText(response);
            msg.setJMSCorrelationID(correlation_id);
            sender.send(msg);
            sender.close();
            ses.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
