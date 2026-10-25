import java.util.Random;

public class Service {

    public static void main(String[] args) {
        
        String logText[] = new String[] {"success", "checking", "fatal", "exception"};

        ILogging logginService = new ServiceProxy();
        Random rand = new Random();

        for (int i = 0; i < 10; i++){

            int tipo = rand.nextInt(0, 3);
            String messagioLog = null;
            if (tipo == 0 || tipo == 1){
                messagioLog = logText[rand.nextInt(0, 2)];
            } else {
                messagioLog = logText[rand.nextInt(2, 4)];
            }

            System.out.println("[Service] Log: " + messagioLog + " - " + String.valueOf(tipo).toString());

            logginService.log(messagioLog, tipo);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
