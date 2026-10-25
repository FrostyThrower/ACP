package meteo;
import java.util.Iterator;

import io.grpc.*;

public class MeteoClient {

    public static void main(String[] args) {

    String target = "localhost:50051";

    ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();
    try {
        StazioneMeteoGrpc.StazioneMeteoBlockingStub stub =
            StazioneMeteoGrpc.newBlockingStub(channel);
        
        CittaRequest req = CittaRequest.newBuilder()
                            .setCitta("Napoli")
                            .setCampioni(3)
                            .build();

    RilevazioneReply response = null;
    try {
        response = stub.rilevaTemperatura(req);
    } catch (StatusRuntimeException e) {
        e.printStackTrace();
        return;
    }

    System.out.println("[unaria] " + response.getCitta() + ": " + response.getTemperaturaC() + "(t=" + response.getTimestampUnix() + ")");
    
    Iterator<RilevazioneReply> it = stub.monitora(req);
    while (it.hasNext()) {
        RilevazioneReply ril = it.next();
        System.out.println("[stream] " + ril.getCitta() + ": " + ril.getTemperaturaC() + "(t=" + ril.getTimestampUnix() + ")");
    }

    } catch (StatusRuntimeException e) {
        e.printStackTrace();
    } finally {
        channel.shutdown();
    }
    }
}
