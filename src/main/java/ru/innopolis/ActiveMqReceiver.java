//package ru.innopolis;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Service;
//import ru.innopolis.jms.MessageBrokerMessage;
//import ru.innopolis.jms.MessageSample;
//
//@Service
//public class ActiveMqReceiver {
//
//    private static final Logger logger = LogManager.getLogger(ActiveMqReceiver.class.getName());
//
//    /**
//     * Получает сообщения из очереди.
//     *
//     * @param messageBrokerMessage
//     */
//    @JmsListener(destination = "${message.broker.tjm}", containerFactory = "jmsFactory")
//    public void receiveCreateOrder(MessageBrokerMessage messageBrokerMessage) {
//        logger.debug("receive message " + messageBrokerMessage);
//        if (messageBrokerMessage instanceof MessageSample) {
//            logger.info("message MessageSample: " + ((MessageSample) messageBrokerMessage));
//        } else {
//            logger.error("Message format is known.");
//        }
//    }
//}
