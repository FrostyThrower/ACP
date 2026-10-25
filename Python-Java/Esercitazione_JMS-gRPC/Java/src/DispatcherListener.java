import javax.jms.*;

public class DispatcherListener implements MessageListener{

    private QueueConnection queueConn;

    public DispatcherListener(QueueConnection queueConn){
        this.queueConn = queueConn;        
    }

    @Override 
    public void onMessage(Message msg){

        TextMessage data = (TextMessage) msg;
        try {
            String text = data.getText();
            String metodo = text.split("#")[0];

            int id_articolo = -1;
            try { id_articolo = Integer.parseInt(text.split("#")[1]);} 
            catch (ArrayIndexOutOfBoundsException e) {}

            Queue queue_toReply = (Queue) data.getJMSReplyTo();
            String correlation_id = data.getJMSCorrelationID();

            Thread th = new DispatcherThread(queueConn, metodo, id_articolo, correlation_id, queue_toReply);
            th.start();



        } catch (JMSException e) {
            e.printStackTrace();
        }

        

        try {
        } catch (Exception e) {
            // TODO: handle exception
        }        



    }

}
