package com.bank.frank_BrainRidge_interview.model;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Account {
    private String accountId;
    private String email;
    private String accountHolderName;
    private Double balance;
    private LocalDateTime createdAt;
    private List<Transaction> transactions;

    // Default constructor
    public Account() {
        this.transactions = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    // Parameterized constructor
    public Account(String accountId, String accountHolderName, String email, Double balance) {
        this();
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.email = email;
        this.balance = balance;
    }

    // Getters and Setters
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public String getOwnerName() { return accountHolderName; }
    public void setOwnerName(String accountHolderName) { this.accountHolderName = accountHolderName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<Transaction> getTransactions() { return transactions; }

}