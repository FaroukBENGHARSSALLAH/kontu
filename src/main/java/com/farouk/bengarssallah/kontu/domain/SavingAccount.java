package com.farouk.bengarssallah.kontu.domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SA")
public class SavingAccount extends Account {

	 private double interest;
	
	 public SavingAccount(){};
	 
	 public SavingAccount(Long reference, Date creationDate, double balance,
			Client client, double interest) {
		super(reference, creationDate, balance, client);
		this.interest = interest;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	
	 
	 
}
