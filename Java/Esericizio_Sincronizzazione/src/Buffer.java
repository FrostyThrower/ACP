/**
 * Buffer
 */
public class Buffer {

    private long content;
    private boolean full;

    // Costruttore
    public Buffer(){
        this.full = false;
    }
    
    public synchronized void produci() throws InterruptedException{
        while (this.full) {
            System.err.printf("[%s-produtttore] Attendo...", Thread.currentThread().getName());
            wait();
        }

        this.content = System.currentTimeMillis();
        this.full = true;
        notifyAll();
    }

    public synchronized void consuma() throws InterruptedException{
        while (!this.full) {
            System.err.printf("[%s-consumatore] Attendo...", Thread.currentThread().getName());
            wait();
        }

        System.out.printf( "[%s] Content:" + this.content, Thread.currentThread().getName());
        this.full = false;
        notifyAll();
    }
}