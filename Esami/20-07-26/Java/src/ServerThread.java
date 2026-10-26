import javax.jms.*;

import org.apache.activemq.filter.FunctionCallExpression.invalidFunctionExpressionException;

public class ServerThread extends Thread{

    private String type;
    private TopicConnection topicConn;
    private Topic topic;

    public ServerThread(String type, TopicConnection topicConn, Topic topic){
        this.type = type;
        this.topicConn = topicConn;
        this.topic = topic;
    }

    public void run(){

        System.out.println("[" + Thread.currentThread().getName() + "] Thread topic-" + type);

        try {
            
            TopicSession topicSess = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicSubscriber subscriber = topicSess.createDurableSubscriber(topic, "sub-"+type);

            subscriber.setMessageListener(new ServerThreadListener());
            
            Thread.sleep(30000);

            subscriber.close();
            topicSess.close();

        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        }



    }

}
