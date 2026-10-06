package prodConsCirc;

public class Buffer_circolare {

    private  int[] items;
    private int tail;
    private int head;
    private int count;

    private int prod_count;

    public Buffer_circolare(int max_lenght){
        this.items = new int[max_lenght];   
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.prod_count = 0;
    }

    public synchronized void put(int value){
        while (this.count == this.items.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

        this.items[this.head] = value;
        this.head++;
        if(this.head == this.items.length){
            this.head = 0;
        }
        this.count++;

        notifyAll();
    }

    public synchronized int take(){
        while (this.count == 0){
            try {
                wait();
                if(this.prod_count == 0){
                    return -1;
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        int value = this.items[this.tail];
        this.tail++;
        this.count--;
        if(this.tail == this.items.length){
            this.tail = 0;
        }

        notifyAll();
        return value;
    }

    public synchronized void add_prod(){
        this.prod_count++;
        System.err.println("Prod_count:" + this.prod_count);
    }

    public synchronized void remove_add(){
        this.prod_count--;
        System.err.println("Prod_count:" + this.prod_count);
        notifyAll();
    }
}
