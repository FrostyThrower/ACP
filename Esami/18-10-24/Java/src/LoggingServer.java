public class LoggingServer extends LogginServerSkeleton{

    @Override 
    public synchronized void log(String messaggioLog, int tipo){
        Thread th = new LogThread(messaggioLog, tipo);
        th.start();
    }

    public static void main(String[] args) {
        LoggingServer server = new LoggingServer();
        server.run_skeleton();
    }



}
