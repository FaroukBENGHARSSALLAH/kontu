package com.farouk.bengarssallah.kontu.implementation;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farouk.bengarssallah.kontu.domain.Account;
import com.farouk.bengarssallah.kontu.domain.CheckingAccount;
import com.farouk.bengarssallah.kontu.domain.Client;
import com.farouk.bengarssallah.kontu.domain.PaymentTransaction;
import com.farouk.bengarssallah.kontu.domain.Transaction;
import com.farouk.bengarssallah.kontu.domain.WithdrawalTransaction;
import com.farouk.bengarssallah.kontu.repository.AccountRepository;
import com.farouk.bengarssallah.kontu.repository.ClientRepository;
import com.farouk.bengarssallah.kontu.repository.TransactionRepository;
import com.farouk.bengarssallah.kontu.service.BankService;

@Service
@Transactional
public class BankServiceImpl implements BankService {

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	

	
	
	@Override
	public Client findClientByReference(Long reference) {
		            Client client = clientRepository.findByReference(reference);
			        if(client  == null)
			      	             throw new RuntimeException("client not found");
			        return client;
	}
	
	@Override
	public Collection<Client> findAllClient() {
		            Collection<Client> clientList = clientRepository.findAll();
			        if(clientList  == null)
			      	             throw new RuntimeException("clientlist not found");
			        return clientList;
	}

	@Override
	public Account findAccountByReference(Long reference) {
					 Account account = accountRepository.findByReference(reference);
			         if(account  == null)
			       	             throw new RuntimeException("account not found");
			         return account;
	  }
	
	
	

	
	
	@Override
	public void addClient(Client client) {
		            try{
		                     clientRepository.save(client);
		                      }
		            catch(Exception e){
			      	             throw new RuntimeException(e.getMessage());
		                    }
	       }

	@Override
	public void addAccount(Account account) {
					try{
			                  accountRepository.save(account);
			             }
			        catch(Exception e){
			 	              throw new RuntimeException(e.getMessage());
			           }
	    }



	@Override
	public void pay(Long reference, double amount) {
		             Account account = findAccountByReference(reference);
		             if(account  == null)
		            	   throw new RuntimeException("account not found");
		             PaymentTransaction paymentTransaction = new PaymentTransaction(new Date(), amount, account);
		             account.setBalance(account.getBalance() + amount );
		             transactionRepository.save(paymentTransaction);
		             accountRepository.save(account);
		             
	   }

	@Override
	public void withdraw(Long reference, double amount) {
					  Account account = findAccountByReference(reference);
			          if(account  == null)
			         	   throw new RuntimeException("account not found");
			          if(account instanceof CheckingAccount){
			        	        if(((CheckingAccount) account).getOverdraft() + account.getBalance() < amount)
			        	                        throw new RuntimeException("insufficient balance");
			                          }
			          WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction(new Date(), amount, account);
			          account.setBalance(account.getBalance() - amount );
			          transactionRepository.save(withdrawalTransaction);
			          accountRepository.save(account);
	           }
	

	@Override
	public void trasnfert(Long referenceAccountSource, Long referenceAccountTarget, double amount) {
		             if(referenceAccountSource.equals(referenceAccountTarget)){
		            	        throw new RuntimeException("you have provided the same reference for the two accounts");
		                }
		             withdraw(referenceAccountSource, amount);
		             pay(referenceAccountTarget, amount);
            	}

	@Override
	public List<Account> accountsList(Long reference) {
		               Client client = clientRepository.findByReference(reference);
		               if(client  == null)
		       	                 throw new RuntimeException("client not found");
		               return  accountRepository.accountList(client.getReference());
             	}
	
	
	@Override
	public List<Transaction> transactionsList(Long reference) {
		               Account account = accountRepository.findByReference(reference);
		               if(account  == null)
		       	                 throw new RuntimeException("account not found");
		               return  transactionRepository.transactionList(account.getReference());
             	}


	@Override
	public void editClient(Client client) {
		clientRepository.save(client);
		
	}

	@Override
	public void deleteClient(Client client) {
		clientRepository.delete(client);
		
	}

	@Override
	public void editAccount(Account account) {
		accountRepository.save(account);
		
	}

	@Override
	public void deleteAccount(Account account) {
		accountRepository.delete(account);
		
	}





}
