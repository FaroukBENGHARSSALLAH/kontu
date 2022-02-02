package com.farouk.bengarssallah.kontu.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import com.farouk.bengarssallah.kontu.export.XLSXExport;
import com.farouk.bengarssallah.kontu.export.PDFExport;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.farouk.bengarssallah.kontu.domain.Client;
import com.farouk.bengarssallah.kontu.domain.SavingAccount;
import com.farouk.bengarssallah.kontu.domain.CheckingAccount;

import java.util.Collection;
import java.util.Date;
import com.farouk.bengarssallah.kontu.domain.Transaction;
import com.farouk.bengarssallah.kontu.exception.AccountNotFoundException;
import com.farouk.bengarssallah.kontu.exception.ClientNotFoundException;

import java.util.List;
import com.farouk.bengarssallah.kontu.domain.Account;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.farouk.bengarssallah.kontu.service.BankService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class AccountController {
    
    private BankService bankService;
    
    
    @RequestMapping({ "/account" })
    public String getAccountPage(final Model model) {
        model.addAttribute("clientlist", this.bankService.findAllClient());
        model.addAttribute("accountlist", this.bankService.accountsList());
        model.addAttribute("view", "account");
        model.addAttribute("loggeduser", this.getPrincipal());
        model.addAttribute("isAdmin", this.isAdmin());
        return "account";
    }
    
    
    @RequestMapping({ "/searchaccount" })
    public String searchAccount(final Model model, final Long reference, final String error, @RequestParam(name = "page", defaultValue = "0") final int page, @RequestParam(name = "size", defaultValue = "5") final int size) {
        try {
            final Account account = this.bankService.findAccountByReference(reference);
            if(account == null) throw new AccountNotFoundException("Account not found");
            model.addAttribute("reference", reference);
            model.addAttribute("view", "account");
            model.addAttribute("account", account);
            model.addAttribute("loggeduser", this.getPrincipal());
            model.addAttribute("isAdmin", this.isAdmin());
            model.addAttribute("clientlist", this.bankService.findAllClient());
            model.addAttribute("accountlist", this.bankService.accountsList());
            
            final List<Transaction> transactions = this.bankService.transactionsList(account.getReference());
            model.addAttribute("transactions", transactions);
            if (error != null) {
                model.addAttribute("transactionexception", error);
            }
        }
        catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "account";
    }
    
    
    @PostMapping({ "/addaccount" })
    public String addAccount(final Model model, final Long reference, final double balance, final String currency, final String type, final Long clientReference) {
    	Account account = null;
    	try {
            final Client client = this.bankService.findClientByReference(clientReference);
            if(client == null) throw new ClientNotFoundException("client not found");
           
            if (type.equals("checking")) {
            	account = this.bankService.addAccount(new CheckingAccount(new Date(), balance, currency, client));
            }
            else if (type.equals("saving")) {
            	account = this.bankService.addAccount(new SavingAccount(new Date(), balance, currency, client));
            }
        }
        catch (Exception e) {
            model.addAttribute("exception", e);
            return "redirect:/account";
        }
        return "redirect:/searchaccount?reference=" + account.getReference();
    }
    

    
    @PostMapping({ "/submittransaction" })
    public String submitTransaction(final Model model, final String type, final double amount, final Long accountreference, final Long toaccountreference) {
        try {
            if (type.equals("payment")) {
                this.bankService.pay(accountreference, amount);
            }
            else if (type.equals("withdraw")) {
                this.bankService.withdraw(accountreference, amount);
            }
            else if (type.equals("transfert")) {
                this.bankService.trasnfert(accountreference, toaccountreference, amount);
            }
        }
        catch (Exception e) {
            return "redirect:/searchaccount?reference=" + accountreference + "&&error=" + e.getMessage();
        }
        return "redirect:/searchaccount?reference=" + accountreference;
    }
    
    
    @RequestMapping({ "/pdfexportaccount" })
    protected ModelAndView pdfexport(final HttpServletRequest request, final HttpServletResponse response, final Long reference) throws Exception {
        final Account account = this.bankService.findAccountByReference(reference);
        return new ModelAndView(new PDFExport(), "account", account);
    }
    
    
    @RequestMapping({ "/xlsxexportaccount" })
    protected ModelAndView xlsxexport(final HttpServletRequest request, final HttpServletResponse response, final Long reference) throws Exception {
        final Account account = this.bankService.findAccountByReference(reference);
        return new ModelAndView(new XLSXExport(), "account", account);
    }
    
    
    @RequestMapping({ "/deleteaccount" })
    public String deleteAccount(final Model model, final Long reference) {
        try {
            final Account account = this.bankService.findAccountByReference(reference);
            this.bankService.deleteAccount(account);
        }
        catch (Exception e) {
            model.addAttribute("exception", e);
            return "redirect:/searchaccount?reference=" + reference;
        }
        return "redirect:/account";
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