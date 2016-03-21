package com.xpcomrade.jms;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;

/**
 * Created by xpcomrade on 2016/3/18.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (message receiver). <br/>
 */
public class MessageReceiver {

    private final JmsTemplate jmsTemplate;

    public MessageReceiver(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void receive(){
        try {
            Message msg = jmsTemplate.receive();
            if (msg instanceof TextMessage) {
                TextMessage textMessage = (TextMessage)msg;
                System.out.println(String.format("MessageReceiver Received: %s", textMessage.getText()));
            } else if (msg instanceof StreamMessage) {
                StreamMessage streamMessage = (StreamMessage)msg;
            } else if (msg instanceof MapMessage) {
                MapMessage mapMessage = (MapMessage)msg;

            } else if (msg instanceof ObjectMessage) {
                ObjectMessage objMessage = (ObjectMessage)msg;

            } else if (msg instanceof BytesMessage) {
                BytesMessage bytesMessage = (BytesMessage)msg;

            } else {
                //ignore...
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
