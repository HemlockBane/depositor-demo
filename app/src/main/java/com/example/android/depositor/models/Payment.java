package com.example.android.depositor.models;

public class PaymentDetails {

    private String accountName;
    private String accountNumber;
    private String depositAmount;
    private String depositorName;
    private String depositorPhoneNumber;
    private String depositorEmail;

    public PaymentDetails(){

    }

    public PaymentDetails(String accountName, String accountNumber, String depositAmount, String depositorName, String depositorPhoneNumber, String depositorEmail){
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.depositAmount = depositAmount;
        this.depositorName = depositorName;
        this.depositorPhoneNumber = depositorPhoneNumber;
        this.depositorEmail = depositorEmail;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getDepositAmount() {
        return depositAmount;
    }

    public String getDepositorName() {
        return depositorName;
    }

    public String getDepositorPhoneNumber() {
        return depositorPhoneNumber;
    }

    public String getDepositorEmail() {
        return depositorEmail;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public void setDepositorPhoneNumber(String depositorPhoneNumber) {
        this.depositorPhoneNumber = depositorPhoneNumber;
    }

    public void setDepositorEmail(String depositorEmail) {
        this.depositorEmail = depositorEmail;
    }
}
