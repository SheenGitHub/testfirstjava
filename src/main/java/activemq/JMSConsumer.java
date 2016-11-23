package activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2016/11/21.
 */
public class JMSConsumer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageConsumer messageConsumer;
        connectionFactory = new ActiveMQConnectionFactory(JMSConsumer.USERNAME,JMSConsumer.PASSWORD,JMSConsumer.BROKEURL);
        try {
            connection = connectionFactory.createConnection();
            connection.start();;
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("HelloWorld");
            messageConsumer = session.createConsumer(destination);
            while (true) {
                TextMessage textMessage = (TextMessage)messageConsumer.receive(100000);
                if (textMessage != null) {
                    System.out.println("Receive Message:"+textMessage.getText());
                }else
                    break;
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
