package com.farouk.bengarssallah.kontu.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACC_TYPE", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Transaction implements Serializable
{
    @Id
    @GeneratedValue
    private Long code;
    private Date date;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "ACC_CODE")
    private Account account;
    
    public Transaction() {
    }
    
    public Transaction(final Date date, final double amount, final Account account) {
        this.date = date;
        this.amount = amount;
        this.account = account;
    }
    
    public Long getCode() {
        return this.code;
    }
    
    public void setCode(final Long code) {
        this.code = code;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(final Date date) {
        this.date = date;
    }
    
    public double getAmount() {
        return this.amount;
    }
    
    public void setAmount(final double amount) {
        this.amount = amount;
    }
    
    public Account getAccount() {
        return this.account;
    }
    
    public void setAccount(final Account account) {
        this.account = account;
    }
}

