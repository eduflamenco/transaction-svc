package com.transaction.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public record Transaction(String user,
                          Double amount,
                          String fromAccount,
                          String toAccount) {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String toString(){
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Error serializando a JSON";
        }
    }
}
