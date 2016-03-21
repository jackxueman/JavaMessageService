package com.xpcomrade.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.UUID;

/**
 * Created by xpcomrade on 2016/3/21.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class TopicSendAndReceive {

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://172.16.1.70:61616");
        Connection  connection = factory.createConnection();
        connection.start();

        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = new ActiveMQTopic("test.topic");

        Queue replyQueue = new ActiveMQQueue("test.topic.replyto");
        MessageConsumer comsumer1 = session.createConsumer(topic);
        comsumer1.setMessageListener(new MessageListener(){
            public void onMessage(Message m) {
                try {
                    MessageProducer producer = session.createProducer(m.getJMSReplyTo());
                    producer.send(session.createTextMessage(m.getJMSMessageID()));
                    System.out.println(String.format("Consumer1 get msg, id [%s], body [%s]", m.getJMSMessageID(), ((TextMessage)m).getText()));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        MessageConsumer comsumer2 = session.createConsumer(topic);
        comsumer2.setMessageListener(new MessageListener(){
            public void onMessage(Message m) {
                try {
                    MessageProducer producer = session.createProducer(m.getJMSReplyTo());
                    producer.send(session.createTextMessage(m.getJMSMessageID()));

                    System.out.println(String.format("Consumer2 get msg, id [%s], body [%s]", m.getJMSMessageID(), ((TextMessage)m).getText()));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //创建一个生产者，然后发送多个消息。
        MessageProducer producer = session.createProducer(topic);
        //消息的发送模式
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        producer.setTimeToLive(1000 * 60 * 60);
        producer.setPriority(9);
        producer.setDisableMessageID(false);
        Message message = null;
        for(int i=0; i<10; i++){
            message = session.createTextMessage("Message:" + i);
            message.setJMSReplyTo(replyQueue);
            String uuid = UUID.randomUUID().toString();
            message.setJMSMessageID(uuid);
            producer.send(message);
        }
    }
}
