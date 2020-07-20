package ru.innopolis;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.innopolis.jms.MessageBrokerMessage;
import ru.innopolis.jms.MessageSample;

import java.util.HashMap;
import java.util.Map;

@Configuration
//@Profile("kafka")
@EnableKafka
@PropertySource(value = {"classpath:application.properties"})
public class KafkaConfig {

    private static final Logger logger = LogManager.getLogger(KafkaConfig.class.getName());

    @Value(value = "${mb.kafka.bootstrapAddress}")
    private String bootstrapAddress;
    @Value(value = "${mb.kafka.sasl.username}")
    private String saslUsername;
    @Value(value = "${mb.kafka.sasl.password}")
    private String saslPassword;
    @Value(value = "${mb.kafka.group.id}")
    private String groupId;

    public KafkaConfig() {
    }

    public ConsumerFactory<String, MessageSample> transactionJsonMessageResultConsumerFactory() {
        Map<String, Object> props = getConfig();
        return new DefaultKafkaConsumerFactory<>(props,
                new StringDeserializer(),
                new JsonDeserializer<>(MessageSample.class));
    }

    public Map<String, Object> getConfig() {
        String jaasFormatTemplate = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";";
        String jaasConfigString = String.format(jaasFormatTemplate,
                saslUsername, saslPassword);
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", "PLAIN");
        props.put("sasl.jaas.config", jaasConfigString);
        return props;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageSample> transactionJsonMessageResultKafkaListenerContainerFactory() {
        logger.info("init transactionJsonMessageResultKafkaListenerContainerFactory");
        ConcurrentKafkaListenerContainerFactory<String, MessageSample> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(transactionJsonMessageResultConsumerFactory());
        return factory;
    }

}
