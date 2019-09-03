package com.farouk.bengarssallah.kontu.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ACC_TYPE", discriminatorType=DiscriminatorType.STRING, length=2)
public abstract class Transaction implements Serializable {
	
	   @Id @GeneratedValue
	private Long code;
	private Date date;
	private double amount;
	
	  @ManyToOne
	  @JoinColumn(name="ACC_CODE")
	private Account account;
	
	public Transaction(){}
	
	public Transaction(Date date, double amount, Account account) {
		this.date = date;
		this.amount = amount;
		this.account = account;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


	

}
