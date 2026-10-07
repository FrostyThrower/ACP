package Java.Esercitazione_ProxySkeleton;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DispatcherSkeleton implements IDispatcher{
    
    DispatcherImpl implDisp;

    public  DispatcherSkeleton(DispatcherImpl implDisp){
        this.implDisp = implDisp;
    }

    public void sendCmd(int cmd){
        this.implDisp.sendCmd(cmd);
    }

    public int getCmd(){
        return this.implDisp.getCmd();
    }

    public void runSkeleton(){
        
        int port = 8080;

        try {
            DatagramSocket server = new DatagramSocket(port);
            System.out.println("[Dispatcher] Listening on 8080...");

            while (true) {
                
                byte[] buffer = new byte[65500];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                server.receive(request);

                WorkerSkeleton wkS = new WorkerSkeleton(server, request, this);
                wkS.start();     

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
