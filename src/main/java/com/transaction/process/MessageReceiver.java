package com.transaction.process;

import com.transaction.configuration.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageReceiver {
    private final RabbitTemplate rabbitTemplate;

    public MessageReceiver(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<String> receiveMessage() {
        List<String> messages = new ArrayList<>();
        try{
            while (true) {
                var received = (String)rabbitTemplate.receiveAndConvert(RabbitConfig.QUEUE_NAME);
                if(received != null)
                    messages.add(received);
                else
                    break;
            }

            if (messages.isEmpty())
                System.out.println("No hay mensajes");

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al recibir mensajes");
        }
        return messages;
    }
}
