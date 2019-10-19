package com.farouk.bengarssallah.kontu.domain;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SA")
public class SavingAccount extends Account
{
    private double interest;
    
    public SavingAccount() {
    }
    
    public SavingAccount(final Long reference, final Date creationDate, final double balance, final Client client, final double interest) {
        super(reference, creationDate, balance, client);
        this.interest = interest;
    }
    
    public double getInterest() {
        return this.interest;
    }
    
    public void setInterest(final double interest) {
        this.interest = interest;
    }
}
