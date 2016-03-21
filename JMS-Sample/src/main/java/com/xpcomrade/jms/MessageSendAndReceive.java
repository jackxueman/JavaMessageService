package com.xpcomrade.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

/**
 * Created by xpcomrade on 2016/3/17.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class MessageSendAndReceive {

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://172.16.1.70:61616");

        Connection connection = factory.createConnection();
        connection.start();

        Queue queue = new ActiveMQQueue("xpcomrade");

        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Message message = session.createTextMessage("Hello JMS!");

        MessageProducer producer = session.createProducer(queue);
        producer.send(message);

        System.out.println("Send Message Completed!");

        MessageConsumer comsumer = session.createConsumer(queue);
        Message recvMessage = comsumer.receive();
        System.out.println(((TextMessage)recvMessage).getText());
    }

}
