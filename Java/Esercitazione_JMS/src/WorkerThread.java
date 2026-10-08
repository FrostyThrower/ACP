import javax.jms.*;

public class WorkerThread extends Thread{

    private Coda coda;
    private MapMessage request;
    private QueueConnection conn;

    private QueueSession session;
    private QueueSender sender;
    private Queue queue;

    public WorkerThread(MapMessage request, QueueConnection conn, Queue queue, Coda coda){
        this.coda = coda;
        this.request = request;
        this.conn = conn;
        this.queue = queue;
    }

    public void run(){

        try {
            String operazione = (String) this.request.getString("operazione");

            if (operazione.compareTo("deposito") == 0){
                System.out.println("[Magazzino] Richiesta di deposito");

                int id_articolo = (int) this.request.getInt("valore");
                this.coda.deposita(id_articolo);
                System.out.println("[Magazzino] Inserito articolo (id:" + id_articolo + ")");
                
            } else if (operazione.compareTo("preleva") == 0){

                try{
                    this.session = this.conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
                    this.sender = this.session.createSender(this.queue);

                    System.out.println("[Magazzino] Richiesta di prelevo");

                    int id_articolo = this.coda.preleva();
                    MapMessage reply = this.session.createMapMessage();

                    reply.setInt("valore", id_articolo);
                this.sender.send(reply);

                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    this.sender.close();
                    this.session.close();
                }
                


            } else {
                System.out.println("[Magazzino] Operazione sconosciuta");
            }
            
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
