package com.farouk.bengarssallah.kontu.domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CA")
public class CheckingAccount extends Account {

	private double overdraft;
	
	public CheckingAccount(){}

	public CheckingAccount(Long reference, Date creationDate, double balance,
			               Client client, double overdraft) {
		super(reference, creationDate, balance, client);
		this.overdraft = overdraft;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}
	
}