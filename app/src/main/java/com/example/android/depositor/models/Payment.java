package com.example.android.depositor.models;

public class Payment {

    private String pushID;
    private String accountName;
    private String accountNumber;
    private String depositAmount;
    private String depositorName;
    private String depositorPhoneNumber;
    private String depositorEmail;

    public Payment(){

    }

    public Payment(String pushID, String accountName, String accountNumber, String depositAmount, String depositorName, String depositorPhoneNumber){
        this.pushID = pushID;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.depositAmount = depositAmount;
        this.depositorName = depositorName;
        this.depositorPhoneNumber = depositorPhoneNumber;
    }
    public Payment(String pushID, String accountName, String accountNumber, String depositAmount, String depositorName, String depositorPhoneNumber, String depositorEmail){
        this.pushID = pushID;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.depositAmount = depositAmount;
        this.depositorName = depositorName;
        this.depositorPhoneNumber = depositorPhoneNumber;
        this.depositorEmail = depositorEmail;
    }

    public String getPushID() {
        return pushID;
    }

    public void setPushID(String pushID) {
        this.pushID = pushID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getDepositorName() {
        return depositorName;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public String getDepositorPhoneNumber() {
        return depositorPhoneNumber;
    }

    public void setDepositorPhoneNumber(String depositorPhoneNumber) {
        this.depositorPhoneNumber = depositorPhoneNumber;
    }

    public String getDepositorEmail() {
        return depositorEmail;
    }

    public void setDepositorEmail(String depositorEmail) {
        this.depositorEmail = depositorEmail;
    }

}
