package com.bank.frank_BrainRidge_interview.service;

import com.bank.frank_BrainRidge_interview.dto.*;
import com.bank.frank_BrainRidge_interview.exception.*;
import com.bank.frank_BrainRidge_interview.model.*;
import com.bank.frank_BrainRidge_interview.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse createAccount(CreateAccountRequest request) {
        // Check if email already exists
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new AccountAlreadyExistsException("Account with email " + request.getEmail() + " already exists");
        }

        // Generate unique account ID
        String accountId = generateAccountId();
        
        // Create new account
        Account account = new Account(accountId, request.getAccountHolderName(), 
                                    request.getEmail(), request.getInitialBalance());
        
        // Save account
        Account savedAccount = accountRepository.save(account);
        
        // Return response
        return mapToAccountResponse(savedAccount);
    }

    public AccountResponse getAccount(String accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + accountId));
        return mapToAccountResponse(account);
    }

    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::mapToAccountResponse)
                .collect(Collectors.toList());
    }

    // Helper method to generate unique account ID
    private String generateAccountId() {
        String accountId;
        do {
            accountId = "ACC" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        } while (accountRepository.existsById(accountId));
        return accountId;
    }

    // Helper method to convert Account to AccountResponse
    private AccountResponse mapToAccountResponse(Account account) {
        return new AccountResponse(
                account.getAccountId(),
                account.getAccountHolderName(),
                account.getEmail(),
                account.getBalance(),
                account.getCreatedAt()
        );
    }
}
