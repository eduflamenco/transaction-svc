package com.transaction.pojo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record CreateAccountRequest(String username, Double balance) {
}
