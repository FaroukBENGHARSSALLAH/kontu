package com.farouk.bengarssallah.kontu.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import java.sql.ResultSet;
import java.util.Set;
import java.sql.Statement;
import java.sql.Connection;
import java.util.HashSet;

import com.farouk.bengarssallah.kontu.domain.SavingAccount;
import com.farouk.bengarssallah.kontu.domain.CheckingAccount;
import com.farouk.bengarssallah.kontu.domain.Transaction;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import com.farouk.bengarssallah.kontu.domain.Account;
import com.farouk.bengarssallah.kontu.domain.Client;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.farouk.bengarssallah.kontu.service.BankService;

import lombok.AllArgsConstructor;

import com.farouk.bengarssallah.kontu.repository.AccountRepository;
import com.farouk.bengarssallah.kontu.repository.ClientRepository;
import com.farouk.bengarssallah.kontu.repository.TransactionRepository;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class HomeController {
    
    private EmbeddedDatabase dataSource;
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;
	    private TransactionRepository transactionRepository;

    private BankService bankService;
    
    @RequestMapping({ "/", "/welcome" })
    public String getHomePage(final Model model) {
        model.addAttribute("view", "welcome");
        model.addAttribute("loggeduser", this.getPrincipal());
        model.addAttribute("isAdmin", this.isAdmin());
        this.reset();
        return "welcome";
    }
    
    private void reset() {
    	
        	
            List<Client> clients = clientRepository.findAll();
            for(Client client : clients) {
            	   if(!client.getName().equals("peter") && 
            			   !client.getName().equals("jhon") && 
            			   !client.getName().equals("dimitry"))
            		   clientRepository.delete(client.getReference());
                  }
            
            List<Account> accounts = accountRepository.findAll();
            for(Account account : accounts) {
            	   if(account.getReference() > 2)
            		   accountRepository.delete(account.getReference());
                  }
            
            List<Transaction> transactions = transactionRepository.findAll();
            for(Transaction transaction : transactions) {
            	   if(transaction.getCode() > 14)
            		   accountRepository.delete(transaction.getCode());
                  }
            
            
        
        
       
    }
    
    
    private String getPrincipal() {
        String userName = null;
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        }
        else {
            userName = principal.toString();
        }
        return userName;
    }
    
    private boolean isAdmin() {
    	final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            final Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();
        	for(GrantedAuthority authority : authorities) {
        	      if(authority.getAuthority().equals("ROLE_ADMIN"))	
        	    	   return true;
        	 }
        }
        else {
            return false;
        }
		return false;
    }
    
}
 