import java.util.Random;

import clusterManager.ClusterManagerGrpc;
import clusterManager.Request;
import clusterManager.StringMessage;
import clusterManager.ClusterManagerGrpc.ClusterManagerBlockingStub;
import clusterManager.Empty;
import io.grpc.*;

public class ClientThread extends Thread{

    private final int NUM_DEPLOY = 4;
    private final int NUM_STOPALL = 1;

    private String[] types = {"gpu", "rt"};
    private String name_thread;
    private Channel channel;

    public ClientThread(String name_thraed, Channel channel){
        this.name_thread = name_thraed;
        this.channel = channel;
    }

    public void run(){

        System.out.println("[" + name_thread + "] Thread avviato...");

        ClusterManagerBlockingStub stub = ClusterManagerGrpc.newBlockingStub(channel);
        Random rand = new Random();

        // Genero richieste deploy
        for (int i = 0; i < NUM_DEPLOY; i++){

            // Genero richiesta

            int id = rand.nextInt(0, 100);
            String type = types[rand.nextInt(0, 2)];

            
            Request req = Request.newBuilder()
                                    .setId(id)
                                    .setName(name_thread)
                                    .setType(type)
                                    .build();
            
            Empty response = stub.deploy(req);

            System.out.println("[" + name_thread + "] Inviata richiesta deploy - id=" + String.valueOf(id).toString() +  
                                " name=" + name_thread + " type=" + type);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Genero richieste sell
        for(int i = 0; i < NUM_STOPALL ; i++){
            String type = types[rand.nextInt(0, 2)];
            StringMessage req = StringMessage.newBuilder().setValue(type).build();        

            Empty response = stub.stopAll(req);
            System.out.println("[" + name_thread + "] Inviata richiesta stopall - type=" + type);
        }
    }

}
