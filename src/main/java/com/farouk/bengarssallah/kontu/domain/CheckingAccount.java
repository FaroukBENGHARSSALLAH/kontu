package com.farouk.bengarssallah.kontu.domain;

package com.farouk.bengarssallah.kontu.domain;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CA")
public class CheckingAccount extends Account
{
    private double overdraft;
    
    public CheckingAccount() {
    }
    
    public CheckingAccount(final Long reference, final Date creationDate, final double balance, final Client client, final double overdraft) {
        super(reference, creationDate, balance, client);
        this.overdraft = overdraft;
    }
    
    public double getOverdraft() {
        return this.overdraft;
    }
    
    public void setOverdraft(final double overdraft) {
        this.overdraft = overdraft;
    }
}
