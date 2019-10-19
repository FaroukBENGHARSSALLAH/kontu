package com.farouk.bengarssallah.kontu.domain;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PT")
public class PaymentTransaction extends Transaction
{
    public PaymentTransaction() {
    }
    
    public PaymentTransaction(final Date date, final double amount, final Account account) {
        super(date, amount, account);
    }
}