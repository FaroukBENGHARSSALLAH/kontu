package com.farouk.bengarssallah.kontu.domain;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CA")
@NoArgsConstructor
public class CheckingAccount extends Account {
   
    public CheckingAccount(final Date creationDate, final double balance, final String currency, final Client client) {
        super(creationDate, balance, currency, client);
    }
    
   
}
