package ru.innopolis.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Session;

/**
 * Sender.
 *
 * @author Ilya_Sukhachev
 */
@Component
public class Sender {
    private Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final String queueName, final String message) {
        logger.info("Sending message {} to queue - {}", message, queueName);
        jmsTemplate.setTimeToLive(30000);
//        jmsTemplate.send(queueName, Session::createTextMessage);
        jmsTemplate.convertAndSend(queueName, message);
    }

    public void sendMessageObject(final String queueName, final Object message) {
        logger.info("Sending message {} to queue - {}", message, queueName);
        jmsTemplate.setTimeToLive(30000);
        jmsTemplate.convertAndSend(queueName, message);
//        jmsTemplate.send(queueName, Session::createObjectMessage);
    }
}
