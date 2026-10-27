package clusterManager;

import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import clusterManager.ClusterManagerGrpc.ClusterManagerImplBase;
import io.grpc.*;
import io.grpc.stub.StreamObserver;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClusterManager extends ClusterManagerImplBase{

    public TopicConnectionFactory topicConnFact;
    private Topic topic_gpu;
    private Topic topic_rt;
    private TopicConnection topicConn;

    public ClusterManager(){

        Hashtable<String, String> prop = new Hashtable<String, String>();

        prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        prop.put("topic.gpu", "gpu");
        prop.put("topic.rt", "rt");

        try {
            
            Context jndiContext = new InitialContext(prop);

            topicConnFact = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
            topic_gpu = (Topic) jndiContext.lookup("gpu");
            topic_rt = (Topic) jndiContext.lookup("rt");
            topicConn = topicConnFact.createTopicConnection();


        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

    @Override 
    public void deploy(Request req, StreamObserver<Empty> responseObserver){

        Thread th = null;

        if (req.getType().compareTo("gpu") == 0){
            th = new ClusterManagerDeployThread(req, responseObserver, topicConn, topic_gpu);
        } else {
            th = new ClusterManagerDeployThread(req, responseObserver, topicConn, topic_rt);
        }
        
        th.start();

    }

    @Override 
    public void stopAll(StringMessage req, StreamObserver<Empty> responseObserver){
        
        Thread th = null;

        if (req.getValue().compareTo("gpu") == 0){
            th = new ClusterManagerStopAllThread(req, responseObserver, topicConn, topic_gpu);
        } else {
            th = new ClusterManagerStopAllThread(req, responseObserver, topicConn, topic_rt);
        }
        
        th.start();

    }

    public static void main(String[] args) {
        
        int port = 8080;
        ExecutorService executor = Executors.newFixedThreadPool(4);

        ClusterManager service = new ClusterManager();

        try {
            
            Server server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                                .executor(executor)
                                .addService(service)
                                .build()
                                .start();
            
            System.out.println("[ClusterManager] Server avviato sulla porta 8080....");

            try {
                server.awaitTermination();
                service.topicConn.close();
            } catch (InterruptedException | JMSException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        


    }

}
