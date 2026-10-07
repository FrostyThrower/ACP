package Java.Esercitazione_ProxySkeleton;

public class Client {

    public static void main(String[] args) {
        
        IDispatcher dispatcher = new DispatcherProxy();
        Thread[] threads = new Thread[5];
        
        for (Thread thread : threads) {
            thread = new WorkerClient(dispatcher);
            thread.start();
        }
    }

}
