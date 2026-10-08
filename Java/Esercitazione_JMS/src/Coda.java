import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Coda {

    private int[] coda;
    private int tail;
    private int head;
    private int count;
    private ReentrantLock lock_coda;
    private Condition cv_prod;
    private Condition cv_cons;

    public Coda(int max_coda){

        this.coda = new int[max_coda];
        this.lock_coda = new ReentrantLock();
        this.cv_prod = this.lock_coda.newCondition();
        this.cv_cons = this.lock_coda.newCondition();

        this.count = 0;
        this.head = 0;
        this.tail = 0;
    }

    public void deposita(int id_articolo){

        this.lock_coda.lock();
        try{

            while(this.count == this.coda.length){
                try {
                    this.cv_prod.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            this.coda[this.head] = id_articolo;
            this.count++;
            this.head++;
            if (this.head == this.coda.length) this.head = 0;

            this.cv_cons.signal();

        } finally {
            this.lock_coda.unlock();
        }
    }

    public int preleva(){

        int output = -1;

        this.lock_coda.lock();
        try {

            while (this.count == 0){
                try {
                    this.cv_cons.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            output = this.coda[this.tail];
            this.tail++;
            this.count--;
            if (this.tail == this.coda.length) this.tail = 0;

            this.cv_prod.signal();           

        } finally {
            this.lock_coda.unlock();
        }

        return output;

    }


}
