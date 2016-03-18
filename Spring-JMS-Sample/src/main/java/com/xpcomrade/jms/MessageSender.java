package com.xpcomrade.jms;

import org.springframework.jms.core.JmsTemplate;

/**
 * Created by xpcomrade on 2016/3/18.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (message producer). <br/>
 */
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(final String text) {
        jmsTemplate.convertAndSend(text);
    }
}
