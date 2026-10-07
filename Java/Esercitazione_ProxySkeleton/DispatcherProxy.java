package Java.Esercitazione_ProxySkeleton;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class DispatcherProxy implements IDispatcher{

    private DatagramSocket socket;
    private final int SERVER_PORT = 8080;
    private InetAddress serverAddress;

    public DispatcherProxy(){
        try {
            this.serverAddress = InetAddress.getByName("localhost");
            this.socket = new DatagramSocket();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void sendCmd(int cmd){

        try {

            String message = new String("sendCmd-"+cmd);
            DatagramPacket request = new DatagramPacket(message.getBytes(), message.getBytes().length, 
                                                        this.serverAddress, this.SERVER_PORT);

            this.socket.send(request);

            byte[] buffer = new byte[65500];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            this.socket.receive(reply);

            String replyMessage = new String(reply.getData(), 0, reply.getLength());
            System.out.println("[Client-" + Thread.currentThread().getName() + "] Receive: " + replyMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }   

    
    public int getCmd(){

        int output = -1;

        try {

            String messsage = new String("getCmd");
            DatagramPacket request = new DatagramPacket(messsage.getBytes(), messsage.getBytes().length,
                                                        this.serverAddress, this.SERVER_PORT);
            
            this.socket.send(request);

            byte[] buffer = new byte[65500];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            this.socket.receive(reply);

            String message = new String(reply.getData(), 0, reply.getLength());

            System.out.println("[Actuator] Recieve cmd: " + message);

            output = Integer.valueOf(message).intValue();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;

    }

}