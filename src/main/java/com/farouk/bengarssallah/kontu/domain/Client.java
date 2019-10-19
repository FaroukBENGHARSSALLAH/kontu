package com.farouk.bengarssallah.kontu.domain;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Client extends AEntity implements Serializable
{
    private Long reference;
    private String name;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private Collection<Account> accounts;
    
    public Client() {
    }
    
    public Client(final Long reference, final String name, final String email) {
        this.reference = reference;
        this.name = name;
        this.email = email;
    }
    
    public Long getReference() {
        return this.reference;
    }
    
    public void setReference(final Long reference) {
        this.reference = reference;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public Collection<Account> getAccounts() {
        return this.accounts;
    }
    
    public void setAccounts(final Collection<Account> accounts) {
        this.accounts = accounts;
    }
}
