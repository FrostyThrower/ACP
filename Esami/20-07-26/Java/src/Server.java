import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class Server {

    public static void main(String[] args){

        Hashtable<String, String> prop = new Hashtable<String, String>();

        prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        prop.put("topic.get", "get");
        prop.put("topic.post", "post");
        prop.put("topic.delete", "delete");

        try {
            Context jndiContext = new InitialContext(prop);

            // ConnFact
            TopicConnectionFactory topicConnFact = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");

            // Topic - get
            Topic topic_get = (Topic) jndiContext.lookup("get");

            // Topic - post
            Topic topic_post = (Topic) jndiContext.lookup("post");

            // Topic - delete
            Topic topic_delete = (Topic) jndiContext.lookup("delete");

            TopicConnection topicConn = topicConnFact.createTopicConnection();
            topicConn.setClientID("Server-1");
            topicConn.start();

            // Thread get
            Thread th_get = new ServerThread("get", topicConn, topic_get);
            th_get.start();

            // Thread post 
            Thread th_post = new ServerThread("post", topicConn, topic_post);
            th_post.start();

            // Thread delete
            Thread th_delete = new ServerThread("delete", topicConn, topic_delete);
            th_delete.start();

            th_get.join();
            th_delete.join();
            th_post.join();

            topicConn.close();

        } catch (NamingException | JMSException | InterruptedException e) {
            e.printStackTrace();
        }


    }


}
