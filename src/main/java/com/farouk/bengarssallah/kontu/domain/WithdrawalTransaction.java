package com.farouk.bengarssallah.kontu.domain;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("WT")
@NoArgsConstructor
public class WithdrawalTransaction extends Transaction {
  
    public WithdrawalTransaction(final Date date, final double amount, final Account account) {
        super(date, amount, account);
    }
    
}