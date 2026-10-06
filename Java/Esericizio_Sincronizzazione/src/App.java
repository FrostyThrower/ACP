import java.util.Scanner;

public class App {
    
    public static void main(String[] args) throws Exception {
        
        Buffer buff = new Buffer();
        Scanner scanner_input = new Scanner(System.in);

        while(true){

            int input = scanner_input.nextInt();
            if(input == 0){
                Thread t = new Produttore(buff);
                t.start();
                System.out.println("[Test] Nuovo thread produttore avviato...");
            } else {
                Thread t = new Consumatore(buff);
                t.start();
                System.out.println("[Test] Nuovo thread consumatore avviato...");
            }
        }
    }
}
