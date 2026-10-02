package dispatcher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.IDispatcher;

public class DispatcherSkeleton implements IDispatcher{

    private int port;
    private IDispatcher dispatcher;

    // Costruttore
    public DispatcherSkeleton(int port, IDispatcher dispatcher){
        this.port = port;
        this.dispatcher = dispatcher;
    }

    @Override
    public void sendCmd(int cmd) {
        dispatcher.sendCmd(cmd);
    }

    @Override
    public int getCmd() {
        return dispatcher.getCmd();
    }

    @Override
    public void runSkeleton() {

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("[DISPACHET ERROR] Runnin on port " + port);

            while (true) {
                
                Socket soc = serverSocket.accept();

                WorkerThread thread = new WorkerThread(soc, dispatcher);
                thread.start();

            }


        } catch (IOException e) {
            // TODO: handle exception
            e.getStackTrace();
        }
    }

}
