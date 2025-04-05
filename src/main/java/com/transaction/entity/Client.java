package com.transaction.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "clients")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "username", nullable = false, length = 10)
    private String username;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Account> accounts;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transaction> transactions;

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + name + '\'' +
                ", nombre='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", createdDate=" + createdDate +
                ", isActive=" + isActive +
                '}';
    }
}

