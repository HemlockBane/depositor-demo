package com.example.android.depositor.models;

public class AccountDetails {
    private String accountName;
    private String accountNumber;

    public AccountDetails(){

    }

    public AccountDetails(String accountName, String accountNumber){

        this.accountName = accountName;
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
