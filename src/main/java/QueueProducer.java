import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueProducer {

    public static void main(String[] args)throws Exception {
        //创建连接工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.188.146:61616");
        //获取链接
        Connection connection = connectionFactory.createConnection();
        //启动链接
        connection.start();
        //获取session(参数1：是否启动事务,参数2：消息确认模式)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列对象
        Queue queue = session.createQueue("test-queue");
        //创建消息生产者
        MessageProducer producer = session.createProducer(queue);
        //创建消息
        TextMessage textMessage = session.createTextMessage("欢迎来到DB联盟");
        //发送消息
        producer.send(textMessage);
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }

}
