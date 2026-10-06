package prodConsCirc;

public class test {
    
    public static void main(String[] args) throws Exception{

        int num_prod = 3;
        int num_cons = 2;
        int num_produzioni = 4;

        Thread[] prod = new Thread[num_prod];
        Thread[] cons = new Thread[num_cons];

        Buffer_circolare buff = new Buffer_circolare(4);
        for (int i = 0; i < num_prod; i++){
            prod[i] = new Thread( new Producer(i, buff, num_produzioni) );
            prod[i].start();

        }

        for (int i = 0; i < num_cons; i++) {
            cons[i] = new Thread(new Consumer(i, buff));
            cons[i].start();
        }

        for (int i = 0; i < prod.length; i++) {
            prod[i].join();
        }

        for (int i = 0; i < cons.length; i++) {
            cons[i].join();
        }
    }




}
