// 
// Decompiled by Procyon v0.5.36
// 

package com.farouk.bengarssallah.kontu.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.farouk.bengarssallah.kontu.export.XLSXExport;
import org.springframework.web.servlet.View;
import com.farouk.bengarssallah.kontu.export.PDFExport;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.farouk.bengarssallah.kontu.domain.Client;
import com.farouk.bengarssallah.kontu.domain.SavingAccount;
import com.farouk.bengarssallah.kontu.domain.CheckingAccount;
import java.util.Date;
import com.farouk.bengarssallah.kontu.domain.Transaction;
import java.util.List;
import com.farouk.bengarssallah.kontu.domain.Account;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.farouk.bengarssallah.kontu.service.BankService;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController
{
    @Autowired
    BankService bankService;
    
    @RequestMapping({ "/account" })
    public String getAccountPage(final Model model) {
        model.addAttribute("clientlist", (Object)this.bankService.findAllClient());
        model.addAttribute("view", (Object)"account");
        model.addAttribute("loggeduser", (Object)this.getPrincipal());
        return "account";
    }
    
    @RequestMapping({ "/searchaccount" })
    public String searchAccount(final Model model, final Long reference, final String error, @RequestParam(name = "page", defaultValue = "0") final int page, @RequestParam(name = "size", defaultValue = "5") final int size) {
        try {
            final Account account = this.bankService.findAccountByReference(reference);
            model.addAttribute("reference", (Object)reference);
            model.addAttribute("view", (Object)"account");
            model.addAttribute("account", (Object)account);
            model.addAttribute("clientlist", (Object)this.bankService.findAllClient());
            final List<Transaction> transactions = (List<Transaction>)this.bankService.transactionsList(account.getReference());
            model.addAttribute("transactions", (Object)transactions);
            if (error != null) {
                model.addAttribute("transactionexception", (Object)error);
            }
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
        }
        return "account";
    }
    
    @RequestMapping({ "/addaccount" })
    public String addAccount(final Model model, final Long reference, final double balance, final String type, @RequestParam(name = "overdraft", defaultValue = "0") final double overdraft, @RequestParam(name = "interest", defaultValue = "0") final double interest, final Long clientref) {
        try {
            final Client client = this.bankService.findClientByReference(clientref);
            if (type.equals("checking")) {
                this.bankService.addAccount((Account)new CheckingAccount(reference, new Date(), balance, client, overdraft));
            }
            else if (type.equals("saving")) {
                this.bankService.addAccount((Account)new SavingAccount(reference, new Date(), balance, client, interest));
            }
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
            return "redirect:/account";
        }
        return "redirect:/searchaccount?reference=" + reference;
    }
    
    @RequestMapping({ "/editaccount" })
    public String editClient(final Model model, final Long reference, final double balance, final String type, final double overdraft, final double interest, final Long clientref) {
        try {
            final Client client = this.bankService.findClientByReference(clientref);
            final Account account = this.bankService.findAccountByReference(reference);
            account.setBalance(balance);
            if (type.equals("checking")) {
                ((CheckingAccount)account).setOverdraft(overdraft);
                this.bankService.editAccount((Account)new CheckingAccount(reference, new Date(), balance, client, overdraft));
            }
            else if (type.equals("saving")) {
                ((SavingAccount)account).setInterest(interest);
                this.bankService.editAccount((Account)new SavingAccount(reference, new Date(), balance, client, interest));
            }
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
            return "redirect:/searchaccount?reference=" + reference + "&&error=" + e.getMessage();
        }
        return "redirect:/searchaccount?reference=" + reference;
    }
    
    @RequestMapping({ "/submittransaction" })
    public String submitTransaction(final Model model, final String type, final double amount, final Long reference, final Long targetaccreference) {
        try {
            if (type.equals("payment")) {
                this.bankService.pay(reference, amount);
            }
            else if (type.equals("withdraw")) {
                this.bankService.withdraw(reference, amount);
            }
            if (type.equals("transfert")) {
                this.bankService.trasnfert(reference, targetaccreference, amount);
            }
        }
        catch (Exception e) {
            return "redirect:/searchaccount?reference=" + reference + "&&error=" + e.getMessage();
        }
        return "redirect:/searchaccount?reference=" + reference;
    }
    
    @RequestMapping({ "/pdfexportaccount" })
    protected ModelAndView pdfexport(final HttpServletRequest request, final HttpServletResponse response, final Long reference) throws Exception {
        final Account account = this.bankService.findAccountByReference(reference);
        return new ModelAndView((View)new PDFExport(), "account", (Object)account);
    }
    
    @RequestMapping({ "/xlsxexportaccount" })
    protected ModelAndView xlsxexport(final HttpServletRequest request, final HttpServletResponse response, final Long reference) throws Exception {
        final Account account = this.bankService.findAccountByReference(reference);
        return new ModelAndView((View)new XLSXExport(), "account", (Object)account);
    }
    
    @RequestMapping({ "/deleteaccount" })
    public String deleteAccount(final Model model, final Long reference) {
        try {
            final Account account = this.bankService.findAccountByReference(reference);
            this.bankService.deleteAccount(account);
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
            return "redirect:/searchaccount?reference=" + reference;
        }
        return "redirect:/account";
    }
    
    @ResponseBody
    @RequestMapping(value = { "/findaccount" }, method = { RequestMethod.GET }, produces = { "application/json" })
    public Account findaccount(final Long reference) {
        return this.bankService.findAccountByReference(reference);
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
}