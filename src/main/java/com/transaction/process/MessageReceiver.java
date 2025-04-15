package com.transaction.process;

import com.rabbitmq.client.GetResponse;
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
        boolean next = true;
        try{
            while (next) {
                next = Boolean.TRUE.equals(rabbitTemplate.execute(channel -> {
                    GetResponse response = channel.basicGet(RabbitConfig.QUEUE_NAME, true);
                    if (response != null) {
                        messages.add(new String(response.getBody()));
                        return true;
                    }
                    return false;
                }));
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
