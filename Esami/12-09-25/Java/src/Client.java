import io.grpc.*;

public class Client {

    public static void main(String[] args) {
    
        String target = "localhost:8080";
        ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5 ; i++){
            String name_thread = "Thread-" + String.valueOf(i).toString();
            Thread th = new ClientThread(name_thread, channel);
            th.start();
            threads[i] = th;
        }

        try {
            for (Thread thread : threads){
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channel.shutdown();
    }
}
