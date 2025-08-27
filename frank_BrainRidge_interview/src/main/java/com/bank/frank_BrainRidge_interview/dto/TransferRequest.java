package com.bank.frank_BrainRidge_interview.dto;

import javax.validation.constraints.*;


public class TransferRequest {
    @NotBlank(message = "From account ID is required")
    private String fromAccountId;

    @NotBlank(message = "To account ID is required")
    private String toAccountId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Positive(message = "Amount must be positive")
    private Double amount;

    private String description;

    //Constructors
    public TransferRequest() {}

    public TransferRequest(String fromAccountId, String toAccountId, Double amount, String description) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.description = description;
    }

    // Getters and Setters
    public String getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
