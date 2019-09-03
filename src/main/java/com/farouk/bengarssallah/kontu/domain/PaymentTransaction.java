package com.farouk.bengarssallah.kontu.domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PT")
public class PaymentTransaction extends Transaction {

	public PaymentTransaction() {}

	public PaymentTransaction(Date date, 
			                   double amount,	Account account) {
		      super(date, amount, account);
	     }
	
	

}
