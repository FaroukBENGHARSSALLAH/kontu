package com.farouk.bengarssallah.kontu.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ACC_TYPE", discriminatorType=DiscriminatorType.STRING, length=2)
public abstract class Account extends AEntity implements Serializable {
      
      private Long reference;
	  private Date creationDate;
	  private double balance;
	  
	  @JsonIgnore
	  @ManyToOne
	  @JoinColumn(name="CLIE_CODE")
	  private Client client;
	  
	  @JsonIgnore
	  @OneToMany(mappedBy="account", cascade=CascadeType.ALL)
	  private Collection<Transaction> transactions;
	  
	  public Account(){}
	  
	  public Account(Long reference, Date creationDate,
	                  double balance, Client client){
		  this.reference = reference;
		  this.creationDate = creationDate;
		  this.balance = balance;
		  this.client = client;
	  }

    
	public Long getReference() {
		return reference;
	}
	public void setReference(Long reference) {
		this.reference = reference;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Collection<Transaction> transactions) {
		this.transactions = transactions;
	}
	  
	  
}