/**
 * Consumatore
 */
public class Consumatore extends Thread{

    private Buffer buff;

    // Costruttore
    public Consumatore(Buffer buff){
        super(Thread.currentThread().getName());
        this.buff = buff;
    }

    @Override 
    public void run(){
        try {
            while(true){
                this.buff.consuma();
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}