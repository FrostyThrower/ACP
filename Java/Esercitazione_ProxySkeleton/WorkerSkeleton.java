package Java.Esercitazione_ProxySkeleton;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

public class WorkerSkeleton extends Thread{

    private DatagramSocket socket;
    private DatagramPacket request;
    private IDispatcher sk;

    public WorkerSkeleton(DatagramSocket socket, DatagramPacket request, IDispatcher sk){
        super();
        this.socket = socket;
        this.request = request;
        this.sk = sk;
    }

    public void run(){

        String message = new String(this.request.getData(), 0, this.request.getLength());
        StringTokenizer messageTokens = new StringTokenizer(message, "-");

        String method = messageTokens.nextToken();

        System.out.println(method);

        if(method.compareTo("sendCmd") == 0){

            String cmd = messageTokens.nextToken();

            sk.sendCmd(Integer.valueOf(cmd).intValue());
            String replyMessage = "ack";

            DatagramPacket reply = new DatagramPacket(replyMessage.getBytes(), replyMessage.getBytes().length, 
                                                    request.getAddress(), request.getPort());

            try {
                socket.send(reply);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (method.compareTo("getCmd") == 0) {
            
            int output = sk.getCmd();
            String replyMessage = String.valueOf(output).toString();


            DatagramPacket reply = new DatagramPacket(replyMessage.getBytes(), replyMessage.getBytes().length,
                                                        request.getAddress(), request.getPort());
            try {
                socket.send(reply);
            } catch (IOException e) {
                e.printStackTrace();
            }            

        } else {
            System.out.println("[Dispatcher] Unknown method!");
        }
        
    }

}