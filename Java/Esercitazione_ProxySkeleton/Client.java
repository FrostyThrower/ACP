package Java.Esercitazione_ProxySkeleton;

public class Client {

    public static void main(String[] args) {
        
        Thread[] threads = new Thread[5];
        
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new WorkerClient();
            threads[i].start();           
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
