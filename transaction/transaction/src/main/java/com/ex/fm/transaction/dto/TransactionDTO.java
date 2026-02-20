package com.ex.fm.transaction.dto;

import java.time.Instant;

public class TransactionDTO {
    private String location;

    private String status;

    private String processStatus;

    private double balance;

    private double transactionAmount;

    private String receiverId;

    private double tax;

    private Instant createdAt;

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public double getBalance() {
        return balance;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public double getTax() {
        return tax;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
