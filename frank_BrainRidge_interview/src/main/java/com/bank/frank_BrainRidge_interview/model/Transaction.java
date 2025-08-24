package com.bank.frank_BrainRidge_interview.model;
import java.time.LocalDateTime;


public class Transaction {

    // Enums defined inside the Transaction class
    public enum TransactionType {
        DEPOSIT,
        TRANSFER,
        WITHDRAWAL
    }

    public enum TransactionStatus {
        PENDING,
        COMPLETED,
        FAILED,
        CANCELLED
    }

    private String transactionId;
    private String fromAccountId;
    private String toAccountId;
    private Double amount;
    private TransactionType type;
    private String description;
    private LocalDateTime timestamp;
    private TransactionStatus status;

    public Transaction() {
        this.timestamp = LocalDateTime.now();
    }

    public Transaction(String transactionId, String fromAccountId, String toAccountId, Double amount, TransactionType type, String description, TransactionStatus status) {
        this();
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters (add all of them)
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getFromAccountId() { return fromAccountId; }
    public void setFromAccountId(String fromAccountId) { this.fromAccountId = fromAccountId; }

    public String getToAccountId() { return toAccountId; }
    public void setToAccountId(String toAccountId) { this.toAccountId = toAccountId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getTimestamp() { return timestamp; }

    public TransactionStatus getStatus() { return status; }
    public void setStatus(TransactionStatus status) { this.status = status; }
}
