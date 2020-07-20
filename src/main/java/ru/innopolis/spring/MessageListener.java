//package ru.innopolis.spring;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//import ru.innopolis.jms.MessageObject;
//
//import javax.jms.Message;
//
//@Component
//public class MessageListener {
//    private Logger logger = LoggerFactory.getLogger(MessageListener.class);
//
//    @JmsListener(destination = "${innopolis.jms.queue}")
//    public void receiveMessage(final Message message) {
//        logger.info("Received message {}", message);
//    }
//
//    @JmsListener(destination = "${innopolis.jms.queue.object}")
//    public void receiveMessage2(MessageObject message) {
//        logger.info("Received message {}", message);
//    }
//
//}
