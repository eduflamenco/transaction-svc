package com.transaction.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "entry")
@Data
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entry_seq")
    @SequenceGenerator(name = "entry_seq", sequenceName = "entry_entry_id_seq", allocationSize = 1)
    @Column(name = "entry_id")
    private Long entryId;

    @Column(name = "account_number", nullable = false, length = 12)
    private String accountNumber;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "transaction_id", nullable = false)
    private UUID transactionId;

    @Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime createdDate = LocalDateTime.now();;

    @ManyToOne
    @JoinColumn(name = "account_number", referencedColumnName = "account_number", insertable = false, updatable = false)
    private Account account; // Relación con la tabla "accounts"

    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Transaction transaction; // Relación con la tabla "transactions"

    public Entry() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "entryId =" + entryId +
                ", accountNumber='" + accountNumber + '\'' +
                ", type ='" + type + '\'' +
                ", amount =" + amount +
                ", transactionId =" + transactionId +
                ", createdDate =" + createdDate +
                '}';
    }

}

