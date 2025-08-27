package com.bank.frank_BrainRidge_interview.repository;

import com.bank.frank_BrainRidge_interview.model.Transaction;;;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {

    private Map<String, Transaction> transactionMap = new ConcurrentHashMap<>();

    public Transaction save(Transaction transaction) {
        transactionMap.put(transaction.getTransactionId(), transaction);
        return transaction;
    }

    public Optional<Transaction> findById(String id) {
        return Optional.ofNullable(transactionMap.get(id));
    }

    public List<Transaction> findAll() {
        return new ArrayList<>(transactionMap.values());
    }

    public List<Transaction> findByAccountId(String accountId) {
        return transactionMap.values().stream()
                .filter(transaction -> 
                    accountId.equals(transaction.getFromAccountId()) || 
                    accountId.equals(transaction.getToAccountId()))
                .sorted((t1, t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()))
                .collect(Collectors.toList());
    }

    public void deleteById(String id) {
        transactionMap.remove(id);
    }
}
