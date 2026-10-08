import javax.jms.*;

public class ClientListener implements  MessageListener{

    @Override 
    public void onMessage(Message arg0){

        MapMessage message = (MapMessage) arg0;
        System.out.println("[Client] Receiving messages...");

        try {
            int id_articolo = message.getInt("valore");
            System.out.println("[Client] Ricevuto un articolo (id:" + id_articolo + ")");
        } catch (JMSException e){
            e.printStackTrace();
        }

    }
}
