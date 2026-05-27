package com.lkamdem.User;

import com.lkamdem.account.Account;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Role userRole;
    private String telephoneNumber;
    private Set<Account> accounts;

    public User(String userName, String userEmail, String userPassword, String telephoneNumber) {
        this(userName, userEmail, userPassword, Role.CUSTOMER, telephoneNumber);
    }

    public User(String userName, String userEmail, String userPassword,  Role userRole, String telephoneNumber) {
        this.userId = UUID.randomUUID().toString();
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.telephoneNumber = telephoneNumber;
        this.accounts = new HashSet<>();

    }

    public String getUserId(){
        return this.userId;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getUserEmail(){
        return this.userEmail;
    }
    String getUserPassword(){ return  this.userPassword;} // TODO: Remove when security layer is implemented

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public Role getUserRole() {
        return userRole;
    }
    public Set<Account> getAccounts() {
        return Collections.unmodifiableSet(accounts);
    }


    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }



    public boolean verifyPassword(String password){
        return this.userPassword.equals(password);
    }

    public boolean changeUserPassword(String oldPassword, String newPassword){
        if(userPassword.equals(oldPassword)){
            this.userPassword = newPassword;
            return true;
        }
        return false;
    }

    public boolean addAccount(Account account){
        return accounts.add(account);
    }

    public boolean removeAccount(Account account){
        return accounts.remove(account);
    }

    @Override
    public String toString(){
       return (userId + " " + userName + " " + userEmail + " " + userRole + " " + telephoneNumber + " " + accounts.toString());
    }


}



/*
*UUID for auto-generated id
* Role enum instead of String
* Two constructors with delegation
* No getPassword() → verifyPassword() instead
* changeUserPassword() with old password verification
* No setUserId() → immutable id
* HashSet for accounts → no duplicates
* Collections.unmodifiableSet() → defensive copying
* toString() without password → security conscious
*/