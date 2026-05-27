package com.lkamdem.transaction;

import com.lkamdem.account.Account;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DESIGN DECISIONS:
 * - UUID for auto-generated transactionId
 * - LocalDateTime.now() for automatic timestamp
 * - destinationAccount can be null for DEPOSIT and WITHDRAW
 * - amount excluded from toString() for privacy
 * - TransactionType enum instead of String
 * - Immutable — no setters, all fields set at creation
 */


public class Transaction {
    private String transactionId;
    private Account originAccount;
    private Account destinationAccount;
    private LocalDateTime date;
    private double amount;
    private TransactionType type;

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", originAccount=" + originAccount +
                ", destinationAccount=" + destinationAccount +
                ", date=" + date +
                ", type=" + type +
                '}';
    }

    public Transaction(Account originAccount, Account destinationAccount, double amount, TransactionType type){
        this.transactionId = UUID.randomUUID().toString();
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.type = type;
        this.date = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public Account getOriginAccount() {
        return originAccount;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
