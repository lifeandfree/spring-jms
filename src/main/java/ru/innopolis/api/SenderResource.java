package ru.innopolis.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.jms.MessageObject;
import ru.innopolis.spring.Sender;

/**
 * SenderResource.
 *
 * @author Ilya_Sukhachev
 */
@RestController
public class SenderResource {

    private Sender sender;
    @Value("${innopolis.jms.queue}")
    private String queueName;

    @Value("${innopolis.jms.queue.object}")
    private String queueNameObject;

    public SenderResource(Sender sender) {
        this.sender = sender;
    }

    @PostMapping("/send")
    public String send(@RequestBody String messageSample){
        sender.sendMessage(queueName, messageSample);
        return "sended";
    }

    @PostMapping("/send2")
    public String sendObject(@RequestBody MessageObject messageSample){
        sender.sendMessageObject(queueNameObject, messageSample);
        return "sended";
    }
}
