package activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2016/11/23.
 */
public class TopicPublisher {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final int SENDNUM = 10;
    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Topic topic;
        Session session;
        Destination destination;
        MessageProducer messageProducer;
        connectionFactory = new ActiveMQConnectionFactory(TopicPublisher.USERNAME,TopicPublisher.PASSWORD,TopicPublisher.BROKEURL);
    try {
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        topic = session.createTopic("myTopic.messages");
        messageProducer = session.createProducer(topic);
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        sendMessage(session,messageProducer);
        session.close();
        connection.stop();
        connection.close();

    } catch (JMSException e) {
        e.printStackTrace();
    }
    }

    public static void sendMessage(Session session,MessageProducer messageProducer) throws JMSException {
        for (int i = 0; i < TopicPublisher.SENDNUM; i++) {
            TextMessage message = session.createTextMessage();
            message.setText("message_" + System.currentTimeMillis());
            messageProducer.send(message);
            System.out.println("Sent message: " + message.getText());
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
