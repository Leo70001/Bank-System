package com.lkamdem.account;

import com.lkamdem.transaction.Transaction;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * DESIGN DECISIONS:
 * - UUID for auto-generated accountNumber
 * - No setters — balance only modified through controlled methods (withdraw/deposit)
 * - Implements BankAccount interface for polymorphism
 * - Elegant transfer() reusing withdraw() and deposit()
 * - Unmodifiable set for defensive copying of transactions
 * - toString() excludes balance for security (logging protection)
 */

public class Account implements BankAccount{
    private String accountNumber;
    private double balance;
    private String userId;
    private Set<Transaction> transactions;

    public Account( String userId) {
        this.accountNumber = UUID.randomUUID().toString();
        this.balance = 0.0;
        this.userId = userId;
        this.transactions = new HashSet<>();
    }

    public Set<Transaction> getTransactions() {
        return Collections.unmodifiableSet(transactions);
    }

    @Override
    public boolean withdraw(double amount) {
       if(amount <= 0){
           return false;
       }
        if(this.balance < amount) {
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean deposit(double amount) {
        if(amount <= 0) {
            return false;
        }
        balance = balance + amount;
        return true;
    }

    @Override
    public boolean transfer(BankAccount target, double amount) {
        return this.withdraw(amount) && target.deposit(amount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", userId='" + userId + '\'' +
                ", transactions=" + transactions +
                '}';
    }

    public boolean addTransaction(Transaction transaction){
            return transactions.add(transaction);
    }

    public boolean removeTransaction(Transaction transaction){
        return transactions.remove(transaction);
    }
}

