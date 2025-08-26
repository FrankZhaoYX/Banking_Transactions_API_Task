package com.bank.frank_BrainRidge_interview.dto;

import java.time.LocalDateTime;


public class AccountResponse {
    private String accountId;
    private String accountHolderName;
    private String email;
    private Double balance;
    private LocalDateTime createdAt;

    // Constructors
    public AccountResponse() {}

    public AccountResponse(String accountId, String accountHolderName, String email, Double balance, LocalDateTime createdAt) {
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.email = email;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public String getAccountHolderName() { return accountHolderName; }
    public void setAccountHolderName(String accountHolderName) { this.accountHolderName = accountHolderName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
