package com.farouk.bengarssallah.kontu.service;

import java.util.Collection;
import java.util.List;

import com.farouk.bengarssallah.kontu.domain.Account;
import com.farouk.bengarssallah.kontu.domain.Client;
import com.farouk.bengarssallah.kontu.domain.Transaction;

public interface BankService {

	  public Client findClientByReference(Long reference); 
	  public Account findAccountByReference(Long reference);
	  
	  public Collection<Client> findAllClient(); 

	  
	  public void addClient(Client client);
	  public void editClient(Client client);
	  public void deleteClient(Client client);
	  
	  public void addAccount(Account account);
	  public void editAccount(Account account);
	  public void deleteAccount(Account account);
	  
	  public void pay(Long reference, double amount);
	  public void withdraw(Long reference, double amount);
	  public void trasnfert(Long referenceAccountSource, Long referenceAccountTarget, double amount);
	  
	  public List<Account> accountsList(Long reference);
	  public List<Transaction> transactionsList(Long code);
	  
}
