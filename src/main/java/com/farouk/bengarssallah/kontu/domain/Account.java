package com.farouk.bengarssallah.kontu.domain;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Collection;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACC_TYPE", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Account extends AEntity implements Serializable
{
    private Long reference;
    private Date creationDate;
    private double balance;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CLIE_CODE")
    private Client client;
    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = { CascadeType.ALL })
    private Collection<Transaction> transactions;
    
    public Account() {
    }
    
    public Account(final Long reference, final Date creationDate, final double balance, final Client client) {
        this.reference = reference;
        this.creationDate = creationDate;
        this.balance = balance;
        this.client = client;
    }
    
    public Long getReference() {
        return this.reference;
    }
    
    public void setReference(final Long reference) {
        this.reference = reference;
    }
    
    public Date getCreationDate() {
        return this.creationDate;
    }
    
    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public double getBalance() {
        return this.balance;
    }
    
    public void setBalance(final double balance) {
        this.balance = balance;
    }
    
    public Client getClient() {
        return this.client;
    }
    
    public void setClient(final Client client) {
        this.client = client;
    }
    
    public Collection<Transaction> getTransactions() {
        return this.transactions;
    }
    
    public void setTransactions(final Collection<Transaction> transactions) {
        this.transactions = transactions;
    }
}
