package dispatcher;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import service.IDispatcher;

public class WorkerThread extends Thread{

    Socket soc;
    IDispatcher dispatcher;

    // Costruttore
    public WorkerThread(Socket soc, IDispatcher dispatcher){
        this.soc = soc;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run(){
        
        try {

            // Riesco a ricevere dati dal client
            DataInputStream dis = new DataInputStream(new BufferedInputStream(soc.getInputStream()));
            
            // Riesco a inviare dati dal client
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(soc.getOutputStream()));

            String method = dis.readUTF();

            int command = -1;

            if(method.equals("sendCmd")){

                command = dis.readInt();

                System.out.println("[WORKER THREAD] Ricevuta sendCmd " + command);

                dispatcher.sendCmd(command);

            } else if(method.equals("getCmd")){

                System.out.println("[WORKER THREAD] Ricevuta getCmd");

                command = dispatcher.getCmd();

                dos.writeInt(command);

            } else {
                System.out.println("[WORKER THREAD] Metodo non riconosciuto");
                
                dos.writeInt(command);
            }
            
            dis.close();
            dos.close();

            soc.close();

        } catch (Exception e) {

            e.getStackTrace();
        }
    }

}
