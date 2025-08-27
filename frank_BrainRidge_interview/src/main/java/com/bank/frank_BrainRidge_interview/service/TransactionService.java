package com.bank.frank_BrainRidge_interview.service;

import com.bank.frank_BrainRidge_interview.dto.*;
import com.bank.frank_BrainRidge_interview.exception.*;
import com.bank.frank_BrainRidge_interview.model.*;
import com.bank.frank_BrainRidge_interview.model.Transaction.TransactionType;
import com.bank.frank_BrainRidge_interview.repository.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Transactional
    public TransferResponse transferFunds(TransferRequest request) {
        if (request.getFromAccountId().equals(request.getToAccountId())) {
            throw new InvalidTransactionException("Cannot transfer to the same account");
        }

        Account fromAccount = accountService.getAccountEntity(request.getFromAccountId());
        Account toAccount = accountService.getAccountEntity(request.getToAccountId());

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds in account: " + request.getFromAccountId());
        }

        // Perform the transfer
        fromAccount.setBalance(fromAccount.getBalance() - request.getAmount());
        toAccount.setBalance(toAccount.getBalance() + request.getAmount());

        // Save updated accounts
        accountService.saveAccount(fromAccount);
        accountService.saveAccount(toAccount);

        // Create transaction record
        String transactionId = generateTransactionId();
        Transaction transaction = new Transaction(
                transactionId,
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount(),
                TransactionType.TRANSFER,
                request.getDescription() != null ? request.getDescription() : "Fund transfer"
        );

        Transaction savedTransaction = transactionRepository.save(transaction);

        // Add transaction to both accounts' transaction history
        fromAccount.getTransactions().add(savedTransaction);
        toAccount.getTransactions().add(savedTransaction);

        return new TransferResponse(
                savedTransaction.getTransactionId(),
                savedTransaction.getFromAccountId(),
                savedTransaction.getToAccountId(),
                savedTransaction.getAmount(),
                savedTransaction.getDescription(),
                savedTransaction.getTimestamp(),
                savedTransaction.getStatus().toString()
        );
    }

    public List<TransactionResponse> getTransactionHistory(String accountId) {
        // Verify account exists
        accountService.getAccountEntity(accountId);
        
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        return transactions.stream()
                .map(this::mapToTransactionResponse)
                .collect(Collectors.toList());
    }

    public TransactionResponse getTransaction(String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        
        if (transaction == null) {
            throw new InvalidTransactionException("Transaction not found with ID: " + transactionId);
        }
        
        return mapToTransactionResponse(transaction);
    }

    private String generateTransactionId() {
        return "TXN" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private TransactionResponse mapToTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getFromAccountId(),
                transaction.getToAccountId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getDescription(),
                transaction.getTimestamp(),
                transaction.getStatus()
        );
    }
}
