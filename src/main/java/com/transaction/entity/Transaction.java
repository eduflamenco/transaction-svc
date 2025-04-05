package com.transaction.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_account_number", referencedColumnName = "account_number", nullable = false)
    @JsonIgnore
    private Account fromAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_account_number", referencedColumnName = "account_number", nullable = false)
    @JsonIgnore
    private Account toAccount;

    @Column(name = "amount")
    private Double amount = 0.00;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    @Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", clienteId=" + (client != null ? client.getId() : null) +
                '}';
    }
}

