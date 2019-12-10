import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueConsumer {
    public static void main(String[] args)throws Exception {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.188.146:61616");
        //获取连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //获取session  (参数1：是否启动事务,参数2：消息确认模式)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列对象
        Queue queue = session.createQueue("test-queue");
        //创建消息消费
        MessageConsumer consumer = session.createConsumer(queue);
        //监听消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("接收到消息："+textMessage.getText());
                    }catch (JMSException e){
                        e.printStackTrace();
                    }
                }

        });
        //等待键盘输入
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();

    }
}
