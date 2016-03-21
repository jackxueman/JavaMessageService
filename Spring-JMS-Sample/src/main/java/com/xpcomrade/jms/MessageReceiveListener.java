package com.xpcomrade.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by xpcomrade on 2016/3/18.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (message receive with messageListener). <br/>
 */
public class MessageReceiveListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                TextMessage msg = (TextMessage)message;
                System.out.println(String.format("MessageReceiveListener Received: %s", msg.getText()));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
