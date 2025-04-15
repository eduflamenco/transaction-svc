package com.transaction.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", length = 12, nullable = false, unique = true)
    private String accountNumber;

    @Column()
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", clienteId=" + (client != null ? client.getId() : null) +
                '}';
    }

    public Account() {

    }

    public Account(String username, Double balance, Client client) {
        this();
        this.accountNumber = generateAccountNumber(username);
        this.balance = balance;
        this.client = client;
    }

    private String generateAccountNumber(String username) {
        return  "AC"+ username.substring(0,1).toUpperCase()+"000"+ ThreadLocalRandom.current().nextInt(1000, 10000);
    }
}

