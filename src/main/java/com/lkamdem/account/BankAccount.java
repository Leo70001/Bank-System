package com.lkamdem.account;

public interface BankAccount {
    boolean withdraw(double amount);
    boolean deposit(double amount);
    boolean transfer(BankAccount target, double amount);
}
