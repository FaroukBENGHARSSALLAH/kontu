package com.farouk.bengarssallah.kontu.service.implementation;

import com.farouk.bengarssallah.kontu.domain.Transaction;
import java.util.List;
import com.farouk.bengarssallah.kontu.domain.WithdrawalTransaction;
import com.farouk.bengarssallah.kontu.domain.CheckingAccount;
import com.farouk.bengarssallah.kontu.domain.PaymentTransaction;
import java.util.Date;
import com.farouk.bengarssallah.kontu.domain.Account;
import java.util.Collection;
import com.farouk.bengarssallah.kontu.domain.Client;
import com.farouk.bengarssallah.kontu.repository.TransactionRepository;
import com.farouk.bengarssallah.kontu.repository.AccountRepository;
import com.farouk.bengarssallah.kontu.repository.ClientRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.farouk.bengarssallah.kontu.service.BankService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BankServiceImpl implements BankService {
	
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    
    @Override
    public Client findClientByReference(final Long reference) {
        final Client client = this.clientRepository.findByReference(reference);
        if (client == null) {
            throw new RuntimeException("client not found");
        }
        return client;
    }
    
    @Override
    public Collection<Client> findAllClient() {
        final List<Client> clientList = this.clientRepository.findAll();
        if (clientList == null) {
            throw new RuntimeException("clientlist not found");
        }
        return clientList;
    }
    
    @Override
    public Account findAccountByReference(final Long reference) {
        final Account account = this.accountRepository.findByReference(reference);
        if (account == null) {
            throw new RuntimeException("account not found");
        }
        return account;
    }
    
    @Override
    @Transactional
    public Client addClient(final Client client) {
        try {
            return this.clientRepository.save(client);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public Account addAccount(final Account account) {
        try {
            return this.accountRepository.save(account);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public void pay(final Long reference, final double amount) {
        final Account account = this.findAccountByReference(reference);
        if (account == null) {
            throw new RuntimeException("account not found");
        }
        final PaymentTransaction paymentTransaction = new PaymentTransaction(new Date(), amount, account);
        account.setBalance(account.getBalance() + amount);
        this.transactionRepository.save(paymentTransaction);
        this.accountRepository.save(account);
    }
    
    @Override
    @Transactional
    public void withdraw(final Long reference, final double amount) {
        final Account account = this.findAccountByReference(reference);
        if (account == null) {
            throw new RuntimeException("account not found");
        }
        if (account instanceof CheckingAccount && (account.getBalance() < amount)) {
            throw new RuntimeException("insufficient balance");
        }
        final WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction(new Date(), amount, account);
        account.setBalance(account.getBalance() - amount);
        this.transactionRepository.save(withdrawalTransaction);
        this.accountRepository.save(account);
    }
    
    @Override
    @Transactional
    public void trasnfert(final Long referenceAccountSource, final Long referenceAccountTarget, final double amount) {
        if (referenceAccountSource.equals(referenceAccountTarget)) {
            throw new RuntimeException("you have provided the same reference for the two accounts");
        }
        this.withdraw(referenceAccountSource, amount);
        this.pay(referenceAccountTarget, amount);
    }
    
    @Override
    public List<Account> accountsList(final Long reference) {
        final Client client = this.clientRepository.findByReference(reference);
        if (client == null) {
            throw new RuntimeException("client not found");
        }
        return this.accountRepository.accountList(client.getReference());
    }
    
    @Override
    public List<Transaction> transactionsList(final Long reference) {
        final Account account = this.accountRepository.findByReference(reference);
        if (account == null) {
            throw new RuntimeException("account not found");
        }
        return this.transactionRepository.transactionList(account.getReference());
    }
    
    @Override
    @Transactional
    public void editClient(final Client client) {
        this.clientRepository.save(client);
    }
    
    @Override
    @Transactional
    public void deleteClient(final Client client) {
        this.clientRepository.delete(client);
    }
    
    @Override
    @Transactional
    public void editAccount(final Account account) {
        this.accountRepository.save(account);
    }
    
    @Override
    @Transactional
    public void deleteAccount(final Account account) {
        this.accountRepository.delete(account);
    }

	@Override
	public List<Account> accountsList() {
		return accountRepository.findAll();
	}
    
}
