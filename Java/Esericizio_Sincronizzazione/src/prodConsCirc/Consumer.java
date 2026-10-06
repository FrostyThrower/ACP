package prodConsCirc;

public class Consumer implements Runnable {
    
    private int id;
    private Buffer_circolare buff;

    public Consumer(int id, Buffer_circolare buff){
        this.id = id;
        this.buff = buff;
    }

    public void run(){

        System.err.println("Consumer" + Thread.currentThread().getName() + " - Consumer avviato...");

        while(true){

            int value = this.buff.take();
            if (value == -1){
                System.err.println("Consumer" + Thread.currentThread().getName() + " - Termino get...");
                return;
            } else {
                System.err.println("Consumer" + Thread.currentThread().getName() + " - Leggo: " + value);
            }
        }   
    } 

}
