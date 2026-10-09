package sensor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

public class SensorHubServer {

    public static void main(String[] args) {
        
        int port = 8080;

        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            Server server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .executor(executor)
                .addService(new SensorHubImpl())
                .build()
                .start();

            System.out.println("[SensorHubServer] Server listening on port " + port + "...");


            try {
                System.out.println("[SensorHubServer] Await for termination...");
                server.awaitTermination();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
