package com.bank.frank_BrainRidge_interview.dto;


import javax.validation.constraints.*;

public class CreateAccountRequest {
    @NotBlank(message = "Account holder name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String accountHolderName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Initial balance is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Initial balance must be non-negative")
    @Digits(integer = 10, fraction = 2, message = "Invalid balance format")
    private Double initialBalance;

    // Constructors
    public CreateAccountRequest() {}

    public CreateAccountRequest(String accountHolderName, String email, Double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.email = email;
        this.initialBalance = initialBalance;
    }

    // Getters and Setters
    public String getAccountHolderName() { return accountHolderName; }
    public void setAccountHolderName(String accountHolderName) { this.accountHolderName = accountHolderName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Double getInitialBalance() { return initialBalance; }
    public void setInitialBalance(Double initialBalance) { this.initialBalance = initialBalance; }
}
