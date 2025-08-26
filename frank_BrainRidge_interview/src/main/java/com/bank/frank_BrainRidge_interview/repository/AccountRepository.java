package com.bank.frank_BrainRidge_interview.repository;

import com.bank.frank_BrainRidge_interview.model.Account;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepository {
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    public Account save(Account account) {
        accounts.put(account.getAccountId(), account);
        return account;
    }

    public Optional<Account> findById(String accountId) {
        return Optional.ofNullable(accounts.get(accountId));
    }

    public List<Account> findAll() {
        return new ArrayList<>(accounts.values());
    }

    public boolean existsById(String accountId) {
        return accounts.containsKey(accountId);
    }

    public boolean existsByEmail(String email) {
        return accounts.values().stream()
                .anyMatch(account -> account.getEmail().equals(email));
    }

    public void deleteById(String accountId) {
        accounts.remove(accountId);
    }

    public long count() {
        return accounts.size();
    }

}
