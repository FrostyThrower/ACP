import javax.jms.*;

public class MagazzinoListener implements MessageListener {

    private final QueueConnection conn;
    private final Coda coda;
    private final Queue queue;

    public MagazzinoListener(Queue queue, QueueConnection conn, Coda coda){
        this.conn = conn;
        this.coda = coda;
        this.queue = queue;
    }

    @Override 
    public void onMessage(Message arg0){

        MapMessage request = (MapMessage) arg0;
        System.out.println("[Magazzino] Receiving messages...");

        WorkerThread th = new WorkerThread(request, this.conn, this.queue, this.coda);
        th.start();
    }

}
