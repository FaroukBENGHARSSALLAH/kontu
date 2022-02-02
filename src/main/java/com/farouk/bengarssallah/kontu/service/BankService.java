package com.farouk.bengarssallah.kontu.service;

import com.farouk.bengarssallah.kontu.domain.Transaction;
import java.util.List;
import java.util.Collection;
import com.farouk.bengarssallah.kontu.domain.Account;
import com.farouk.bengarssallah.kontu.domain.Client;

public interface BankService {
	
    Client findClientByReference(final Long reference);
    
    Account findAccountByReference(final Long reference);
    
    Collection<Client> findAllClient();
    
    Client addClient(final Client client);
    
    void editClient(final Client client);
    
    void deleteClient(final Client client);
    
    Account addAccount(final Account account);
    
    void editAccount(final Account account);
    
    void deleteAccount(final Account account);
    
    void pay(final Long accountId, final double amount);
    
    void withdraw(final Long accountId, final double amount);
    
    void trasnfert(final Long fromAccountId, final Long toAccountId, final double amount);
    
    List<Account> accountsList(final Long id);
    
    List<Account> accountsList();
    
    List<Transaction> transactionsList(final Long id);
}
