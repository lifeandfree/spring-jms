package ru.innopolis.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.innopolis.jms.MessageBrokerMessage;

@Service
//@Profile("kafka")
public class KafkaConsumer {

    private static final Logger logger = LogManager.getLogger(KafkaConsumer.class.getName());

    @KafkaListener(topics = "${message.broker.tjm}", containerFactory = "transactionJsonMessageResultKafkaListenerContainerFactory")
    public void messageListener(MessageBrokerMessage message) {
        logger.info("Received message " + message);

    }

}
