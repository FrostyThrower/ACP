import javax.jms.*;

public class DispatcherListener implements MessageListener{

    private QueueConnection queueConn;

    public DispatcherListener(QueueConnection queueConn){
        this.queueConn = queueConn;
    }

    @Override
    public void onMessage(Message data) {

        TextMessage msg = (TextMessage) data;
        
        try {
            String text = msg.getText();
            String method = text.split("#")[0];
            int id_articolo;
            try {
                id_articolo = Integer.parseInt(text.split("#")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                id_articolo = -1;
            }

            Queue queue = (Queue) msg.getJMSReplyTo();  
            String correlation_id = msg.getJMSCorrelationID();

            Thread thd = new DispatcherThread(queueConn, method, id_articolo, queue, correlation_id);
            thd.start();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
