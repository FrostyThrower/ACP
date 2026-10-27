package clusterManager;

import javax.jms.*;
import javax.management.JMException;

import io.grpc.stub.StreamObserver;

public class ClusterManagerDeployThread extends Thread {

    private StreamObserver<Empty> responseObserver;
    private TopicConnection topicConn; 
    private Topic topic;
    private Request req;

    public ClusterManagerDeployThread(Request req, StreamObserver<Empty> responseObserver
                                        , TopicConnection topicConn, Topic topic){
        this.req = req;
        this.responseObserver = responseObserver;
        this.topicConn = topicConn;
        this.topic = topic;
    }

    @Override 
    public void run(){

        String WHO_I_AM = Thread.currentThread().getName();
        System.out.println("[ClusterManager-" + WHO_I_AM + "] Ricevuto richiesta di tipo DEPLOY");

        String id = String.valueOf(req.getId()).toString();
        String name = req.getName();


        try {
            TopicSession sess = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicPublisher publisher = sess.createPublisher(topic);

            String string_to_send = "deploy#" + id + "#" + name;
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
