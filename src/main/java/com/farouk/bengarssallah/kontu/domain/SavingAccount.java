package com.farouk.bengarssallah.kontu.domain;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("SA")
@NoArgsConstructor
public class SavingAccount extends Account {
    
    public SavingAccount(final Date creationDate, final double balance, final String currency, final Client client) {
        super(creationDate, balance, currency, client);
    }
  
}
