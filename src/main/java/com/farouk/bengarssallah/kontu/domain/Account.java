package com.farouk.bengarssallah.kontu.domain;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Collection;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
public abstract class Account extends AEntity implements Serializable {
	
    private Date creationDate;
    private double balance;
    private String currency;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CLIE_CODE")
    private Client client;
    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = { CascadeType.ALL })
    private Collection<Transaction> transactions;
    
    public Account(final Date creationDate, final double balance, final String currency, final Client client) {
        this.creationDate = creationDate;
        this.balance = balance;
        this.currency = currency;
        this.client = client;
    }

}