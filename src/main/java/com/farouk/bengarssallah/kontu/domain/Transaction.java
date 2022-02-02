package com.farouk.bengarssallah.kontu.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
public abstract class Transaction implements Serializable {
    @Id
    @GeneratedValue
    private Long code;
    private Date date;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "ACC_CODE")
    private Account account;
    
    public Transaction(final Date date, final double amount, final Account account) {
        this.date = date;
        this.amount = amount;
        this.account = account;
    }

 
 }