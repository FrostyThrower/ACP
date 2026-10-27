package clusterManager;

import javax.jms.*;

import io.grpc.stub.StreamObserver;

public class ClusterManagerStopAllThread extends Thread {

    private StreamObserver<Empty> responseObserver;
    private TopicConnection topicConn; 
    private Topic topic;
    private StringMessage msg;

    public ClusterManagerStopAllThread(StringMessage msg, StreamObserver<Empty> responseObserver, 
                                        TopicConnection topicConn, Topic topic){
        this.msg = msg;
        this.responseObserver = responseObserver;
        this.topicConn = topicConn;
        this.topic = topic;
    }

    @Override 
    public void run(){

        String WHO_I_AM = Thread.currentThread().getName();
        System.out.println("[ClusterManager-" + WHO_I_AM + "] Ricevuto richiesta di tipo STOPALL");

        try {
            TopicSession sess = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicPublisher publisher = sess.createPublisher(topic);

            String string_to_send = "deploy";
            TextMessage data_to_send = sess.createTextMessage();
            
            data_to_send.setText(string_to_send);
            publisher.send(data_to_send);

            publisher.close();
            sess.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }

        Empty empty = Empty.newBuilder().build();
        responseObserver.onNext(empty);
        responseObserver.onCompleted();
        

    }

}
