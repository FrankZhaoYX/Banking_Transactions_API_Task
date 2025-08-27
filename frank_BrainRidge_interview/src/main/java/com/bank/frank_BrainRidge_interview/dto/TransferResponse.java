package com.bank.frank_BrainRidge_interview.dto;

import java.time.LocalDateTime;


public class TransferResponse {
    private String transactionId;
    private String fromAccountId;
    private String toAccountId;
    private Double amount;
    private String description;
    private LocalDateTime timestamp;
    private String status;

    //Constructors

    public TransferResponse() {}

    public TransferResponse(String transactionId, String fromAccountId, String toAccountId, Double amount, String description, LocalDateTime timestamp, String status) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.description = description;
        this.timestamp = timestamp;
        this.status = status;
    }

    //Getters and Setters
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getFromAccountId() { return fromAccountId; }
    public void setFromAccountId(String fromAccountId) { this.fromAccountId = fromAccountId; }

    public String getToAccountId() { return toAccountId; }
    public void setToAccountId(String toAccountId) { this.toAccountId = toAccountId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

}
