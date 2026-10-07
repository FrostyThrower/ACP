package Java.Esercitazione_ProxySkeleton;

import java.util.Random;

public class WorkerClient extends Thread{

    IDispatcher disp;

    public  WorkerClient(){
        this.disp = new DispatcherProxy();
    }

    public void run(){

        for (int i = 0; i < 3; i++) {
            
            Random ran = new Random();
            int choice = -1;
            
            try {
                Thread.sleep(1000*ran.nextLong(2, 4));  
                choice = ran.nextInt(0, 4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("[Client-" + Thread.currentThread().getName() + "] Richiesta di comando " + choice);
            disp.sendCmd(choice);
        }
    }

}
