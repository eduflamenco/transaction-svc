package com.transaction.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.pojo.TransactionRequest;
import com.transaction.repository.ExecuteTransaction;
import org.springframework.stereotype.Service;

@Service
public class MakeTransaction {

    private final MessageReceiver messageReceiver;
    private final ExecuteTransaction executeTransaction;
    private final ObjectMapper objectMapper;

    public MakeTransaction(MessageReceiver messageReceiver, ExecuteTransaction executeTransaction) {
        this.messageReceiver = messageReceiver;
        this.executeTransaction = executeTransaction;
        this.objectMapper = new ObjectMapper();
    }

    public void makeTransaction() {
        TransactionRequest transaction;
        var received = messageReceiver.receiveMessage();
        if(!received.isEmpty()) {
            try {
                for (var message : received) {
                    transaction = objectMapper.readValue(message, TransactionRequest.class);
                    System.out.println("Mensjae leido: "+ transaction);
                    String result = executeTransaction.execute(transaction);
                    System.out.println(result);
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
