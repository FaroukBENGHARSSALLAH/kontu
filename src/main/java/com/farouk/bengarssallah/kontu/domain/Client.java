package com.farouk.bengarssallah.kontu.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client extends AEntity implements Serializable { 
	
	private Long reference;
	private String name;
	private String email;
	
	@JsonIgnore
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<Account> accounts;
    
    public Client() {}
    
	public Client(Long reference, String name, String email) {
		this.reference = reference;
		this.name = name;
		this.email = email;
	}

	public Long getReference() {
		return reference;
	}
	public void setReference(Long reference) {
		this.reference = reference;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}
    
    

}
