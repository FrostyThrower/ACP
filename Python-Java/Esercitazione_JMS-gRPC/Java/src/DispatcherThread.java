import javax.jms.*;
import javax.management.JMException;

import io.grpc.*;
import server.ServerGrpc;
import server.ServerOuterClass.Empty;
import server.ServerOuterClass.Reply;
import server.ServerOuterClass.Request;

public class DispatcherThread extends Thread {

    private QueueConnection queueConn;
    private String method;
    private int id_articolo;
    private String correlation_id;
    private Queue queue_to_reply;
    private final String WHO_I_AM = "[" + Thread.currentThread().getName() + "]";

    public DispatcherThread(QueueConnection queueConn, String method, int id_articolo, String correlation_id, Queue queue_to_reply){
        this.queueConn = queueConn;
        this.method = method;
        this.id_articolo = id_articolo;
        this.correlation_id = correlation_id;
        this.queue_to_reply = queue_to_reply;
    }

    @Override 
    public void run(){
       
        String target = "localhost:8080";

        ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();

        ServerGrpc.ServerBlockingStub stub = ServerGrpc.newBlockingStub(channel);
        
        String text = "";


        if( method.compareTo("deposita") == 0){
            
            System.out.println(WHO_I_AM + " Ricevuta richiesta deposita");

            Request req = Request.newBuilder()
                                    .setIdArticolo(id_articolo)
                                    .build();
            Reply repl = null;
            try {
                repl = stub.deposita(req);
            } catch (StatusRuntimeException e) {
                e.printStackTrace();
            }

            text = repl.getResponse();

        } else if (method.compareTo("preleva") == 0){

            System.out.println(WHO_I_AM + " Ricevuta richiesta preleva");
            Empty empty = Empty.newBuilder().build();
            Reply repl = null;
            try {
                repl = stub.preleva(empty);
            } catch (StatusRuntimeException e) {
                e.printStackTrace();
            }

            text = repl.getResponse();
        } else {
            System.out.println("ERRORE: Metodo sconosciuto");
        }

        try{
            QueueSession queueSess = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueSender sender = queueSess.createSender(queue_to_reply);

            TextMessage msg = queueSess.createTextMessage();
            msg.setText(text);
            msg.setJMSCorrelationID(correlation_id);
            sender.send(msg);
            sender.close();
            queueSess.close();               

        } catch (JMSException e) {
            e.printStackTrace();
        }

        channel.shutdown();


    }
}
