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

import java.util.Collection;
import java.util.Date;
import com.farouk.bengarssallah.kontu.domain.Account;
import com.farouk.bengarssallah.kontu.domain.Client;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.farouk.bengarssallah.kontu.service.BankService;

import lombok.AllArgsConstructor;

import com.farouk.bengarssallah.kontu.repository.AccountRepository;
import com.farouk.bengarssallah.kontu.repository.ClientRepository;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class HomeController {
    
    private EmbeddedDatabase dataSource;
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;
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
    	
        try {
            this.clearDatabase();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        final Client cl = this.clientRepository.save(new Client("peter", "cl@gmai.com"));
        final Client cl2 = this.clientRepository.save(new Client("jhon", "cl@gmai.com"));
        final Client cl3 = this.clientRepository.save(new Client("dimitry", "cl@gmai.com"));
        final Account ac = this.accountRepository.save(new CheckingAccount(new Date(), 900.0, "CAD", cl));
        final Account ac2 = this.accountRepository.save(new SavingAccount(new Date(), 100.0, "CAD", cl2));
        
        this.bankService.pay(ac.getReference(), 2.0);
        this.bankService.pay(ac.getReference(), 2.0);
        this.bankService.pay(ac.getReference(), 2.0);
        this.bankService.pay(ac.getReference(), 2.0);
        this.bankService.pay(ac.getReference(), 2.0);
        this.bankService.pay(ac.getReference(), 2.0);
        this.bankService.pay(ac.getReference(), 2.0);
        this.bankService.pay(ac.getReference(), 2.0);
        this.bankService.pay(ac.getReference(), 2.0);
        this.bankService.pay(ac.getReference(), 2.0);
        this.bankService.pay(ac.getReference(), 2.0);
        this.bankService.pay(ac.getReference(), 2.0);
        this.bankService.withdraw(ac.getReference(), 5.0);
        
        this.bankService.trasnfert(ac.getReference(), ac2.getReference(), 20.0);
    }
    
    
    public void clearDatabase() throws SQLException {
        final Connection c = this.dataSource.getConnection();
        final Statement s = c.createStatement();
        s.execute("SET REFERENTIAL_INTEGRITY FALSE");
        final Set<String> tables = new HashSet<String>();
        ResultSet rs = s.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC'");
        while (rs.next()) {
            final String rss = rs.getString(1);
            if (!rss.equals("USER") && !rss.equals("PERSISTENT_LOGIN")) {
                tables.add(rss);
            }
        }
        rs.close();
        for (final String table : tables) {
            s.executeUpdate("TRUNCATE TABLE " + table);
        }
        final Set<String> sequences = new HashSet<String>();
        rs = s.executeQuery("SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SEQUENCES WHERE SEQUENCE_SCHEMA='PUBLIC'");
        while (rs.next()) {
            sequences.add(rs.getString(1));
        }
        rs.close();
        for (final String seq : sequences) {
            s.executeUpdate("ALTER SEQUENCE " + seq + " RESTART WITH 1");
        }
        s.execute("SET REFERENTIAL_INTEGRITY TRUE");
        s.close();
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
 