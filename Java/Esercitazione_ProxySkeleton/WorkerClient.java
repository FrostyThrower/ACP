package Java.Esercitazione_ProxySkeleton;

import java.util.Random;

public class WorkerClient extends Thread{

    IDispatcher disp;

    public  WorkerClient(IDispatcher disp){
        this.disp = disp;
    }

    public void run(){

        Random ran = new Random();
        int choice = -1;
        
        try {
            Thread.sleep(ran.nextLong(2, 4));  
            choice = ran.nextInt(0, 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[Client-" + Thread.currentThread().getName() + "] Richiesta di comando " + choice);
        disp.sendCmd(choice);
    }

}
