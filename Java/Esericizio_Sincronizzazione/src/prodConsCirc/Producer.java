package prodConsCirc;

import java.util.Random;

public class Producer implements Runnable{
    
    private int id;
    private Buffer_circolare buff;
    private int produz_elementi;
    private Random ran = new Random();

    public  Producer(int id, Buffer_circolare buff, int produz_elementi){
        this.id = id+1;
        this.buff = buff;
        this.produz_elementi = produz_elementi;
    }   

    public void run(){

        System.err.println("Producer" + Thread.currentThread().getName() + " - Producer avviato...");

        this.buff.add_prod();

        
        for (int i = 0; i < this.produz_elementi; i++) {
            
            int value_to_put = (this.id * 1000) + i;
            System.err.println("Producer" + Thread.currentThread().getName() + "- Prodotto: " + value_to_put);
            this.buff.put(value_to_put);

            try {
                Thread.sleep(this.ran.nextLong(1000));
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

        System.err.println("Producer" + Thread.currentThread().getName() + " - Termino...");
        this.buff.remove_add();
    
    }
}
