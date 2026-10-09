package sensor;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import io.grpc.*;

public class SensorHubClient {

    private final SensorHubGrpc.SensorHubBlockingStub blockingStub;

    public SensorHubClient(Channel channel){
        blockingStub = SensorHubGrpc.newBlockingStub(channel);
    }

    public void run(){

        System.out.println("[Client] ReadingRequest...");

        for (int i = 0; i < 5; i++) {
            
            Reading request = Reading.newBuilder()
                                        .setSensorId(String.valueOf(i+1).toString())
                                        .setType("pressure")
                                        .setValue(i+1 * 1000)
                                        .setTimestamp(i+1 * 100)
                                        .build();

            Ack response;
            try {
                response = blockingStub.registerReading(request);
            } catch (StatusRuntimeException e) {
                System.out.println("[Client] RPC failed: " + e.getStatus());
                return;
            }                                       
            System.out.println("[Client] Response: " + response);
        }
            
        Reading request = Reading.newBuilder()
                                    .setSensorId("1")
                                    .setType("pressure")
                                    .setValue(2222)
                                    .setTimestamp(222)
                                    .build();

        Ack response1;
        try {
            response1 = blockingStub.registerReading(request);
        } catch (StatusRuntimeException e) {
            System.out.println("[Client] RPC failed: " + e.getStatus());
            return;
        }                          
        System.out.println("[Client] Response: " + response1);       


        Reading request1 = Reading.newBuilder()
                                    .setSensorId(String.valueOf(6).toString())
                                    .setType("cacca")
                                    .setValue(6 * 1000)
                                    .setTimestamp(6 * 100)
                                    .build();
        Ack response2;
        try {
            response2 = blockingStub.registerReading(request1);
        } catch (StatusRuntimeException e) {
            System.out.println("[Client] RPC failed: " + e.getStatus());
            return;
        }                                       
        System.out.println("[Client] Response: " + response2);       


        SensorQuery request2 = SensorQuery.newBuilder()
                                            .setSensorId("1")
                                            .setMaxResults(2)
                                            .build();
        Iterator<Reading> iterator = blockingStub.streamReadings(request2);
        try {
            while(iterator.hasNext()){
                Reading r = iterator.next();
                System.out.println("[Client] Response: " + r.toString());
            }
        } catch (StatusRuntimeException e) {
            System.out.println("[Client] RPC failed: " + e.getStatus());
            return;            
        }
        
    
    }

    


    public static void main(String[] args) throws Exception {
        
        String target = "localhost:8080";
        ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();
        try {
            SensorHubClient client = new SensorHubClient(channel);
            client.run();
        } finally {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }


}
