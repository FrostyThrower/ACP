package Java.Esercitazione_ProxySkeleton;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DispatcherImpl implements IDispatcher {
    
    private int[] coda;
    private int head;
    private int tail;
    private ReentrantLock lock;
    private Condition cv_prod;
    private Condition cv_cons; 
    


    public DispatcherImpl(int lenght){
        this.coda = new int[lenght];
        this.head = 0;
        this.tail = 0;

        this.lock = new ReentrantLock();
        this.cv_prod = this.lock.newCondition();
        this.cv_cons = this.lock.newCondition();
    }

    public void sendCmd(int cmd){
        
        this.lock.lock();
        try{

            while( (this.head - this.tail) == this.coda.length ){
                try {
                    this.cv_prod.await();  
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            this.coda[this.head] = cmd;
            this.head++;
            if (this.head == this.coda.length) this.head = 0;
            
            this.cv_cons.signal();
        } finally {
            this.lock.unlock();
        }

    }

    public int getCmd(){

        int output;

        this.lock.lock();
        try{

            while ((this.head - this.tail) == 0) {
                try{
                    this.cv_cons.await();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            output = this.coda[this.tail];
            this.tail++;
            if (this.tail == this.coda.length) this.tail = 0;
            
            this.cv_prod.signal();

        } finally {
            this.lock.unlock();
        }

        return output;
    }
}
