/**
 * Produttore
 */
public class Produttore extends Thread{

    private Buffer buff;

    // Costuttore
    public Produttore(Buffer buff){
        super(Thread.currentThread().getName());
        this.buff = buff;
    }

    @Override 
    public void run(){
        try{
            while(true){
                this.buff.produci();
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}
