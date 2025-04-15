package com.transaction.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonSerialize
public class AccountResponse {
    private String accountNumber;
    private String username;
    private Double balance;
    private LocalDateTime createdDate;
}
