package com.ex.fm.wise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class Transaction {


    public Transaction() {
    }

    public Transaction(String location, String status, String processStatus, double balance,
                       double transactionAmount, String receiverId, double tax, Instant createdAt) {
        this.location = location;
        this.status = status;
        this.processStatus = processStatus;
        this.balance = balance;
        this.transactionAmount = transactionAmount;
        this.receiverId = receiverId;
        this.tax = tax;
        this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String location;

    private String status;

    private String processStatus;

    private double balance;

    private double transactionAmount;

    private String receiverId;

    private double tax;

    private Instant createdAt;

    public Long getTransactionId() {
        return transactionId;
    }

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
