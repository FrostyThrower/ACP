package sensor;

import java.util.List;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class SensorHubImpl extends SensorHubGrpc.SensorHubImplBase{

    private StrutturaMem mem;

    public SensorHubImpl(){
        mem = new StrutturaMem();       
    }

    @Override 
    public void registerReading(Reading req, StreamObserver<Ack> responseObserver){
        System.out.println("[SensorHub] Invocato registerReading...");

        // Valido reading
        if (req.getSensorId() == null){
            Ack reply = Ack.newBuilder()
                        .setAccepted(false)
                        .setMessage("sensor_id null")
                        .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            return;
        }

        if (!List.of("temperature", "humidity", "pressure").contains(req.getType())){
            Ack reply = Ack.newBuilder()
                        .setAccepted(false)
                        .setMessage("type unknown")
                        .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            return;           
        }

        if (req.getTimestamp() <= 0){
            Ack reply = Ack.newBuilder()
                        .setAccepted(false)
                        .setMessage("timestamp < 0")
                        .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            return;  
        }

        mem.add_element(req);

        Ack reply = Ack.newBuilder()
                    .setAccepted(true)
                    .setMessage("Reading aggiunto correttamente!")
                    .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override 
    public void streamReadings(SensorQuery req, StreamObserver<Reading> responseObserver){

        System.out.println("[SensorHub] Invocato streamingReadings...");

        List<Reading> list_readings = mem.get_element(req.getSensorId());
        if (list_readings == null){
            responseObserver.onError(Status.NOT_FOUND.withDescription("Sensor_id null").asRuntimeException());
            return;
        }

        for (int i = 0; i < list_readings.size() && i < req.getMaxResults(); i++) {
            Reading red = list_readings.get(i);
            responseObserver.onNext(red);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        responseObserver.onCompleted();
    }

    @Override 
    public void computeStats(SensorQuery req, StreamObserver<Stats> responseObserver){
        
        System.out.println("[SensorHub] Invocato computeStats...");

        List<Reading> list_readings = mem.get_element(req.getSensorId());
        if (list_readings == null){
            responseObserver.onError(Status.NOT_FOUND.withDescription("Sensor_id null").asRuntimeException());
            return;
        }

        int count = 0;
        double max = Double.NEGATIVE_INFINITY;
        int sum = 0;

        for (int i = 0; i < list_readings.size() && i < req.getMaxResults(); i++) {
            sum += list_readings.get(i).getValue();
            count++;
            if (max < list_readings.get(i).getValue()){
                max = list_readings.get(i).getValue();
            }
        }

        double average = sum / count;
        Stats output = Stats.newBuilder()
                        .setAverage(average)
                        .setCount(count)
                        .setSensorId(req.getSensorId())
                        .setMax(max)
                        .build();

        responseObserver.onNext(output);
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        responseObserver.onCompleted();
    }

}