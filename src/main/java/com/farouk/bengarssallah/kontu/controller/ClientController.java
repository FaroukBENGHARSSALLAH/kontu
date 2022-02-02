package com.farouk.bengarssallah.kontu.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.farouk.bengarssallah.kontu.export.XLSXExport;
import com.farouk.bengarssallah.kontu.export.PDFExport;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.farouk.bengarssallah.kontu.domain.Account;

import java.util.Collection;
import java.util.List;
import com.farouk.bengarssallah.kontu.domain.Client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.farouk.bengarssallah.kontu.service.BankService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ClientController {
    
    private BankService bankService;
    
    
    @RequestMapping({ "/client" })
    public String getClientPage(final Model model) {
        model.addAttribute("view", "client");
        model.addAttribute("loggeduser", this.getPrincipal());
        model.addAttribute("isAdmin", this.isAdmin());
        return "client";
    }
    
    
    @RequestMapping({ "/searchclient" })
    public String searchClient(final Model model, final Long reference, final String error, @RequestParam(name = "page", defaultValue = "0") final int page, @RequestParam(name = "size", defaultValue = "5") final int size) {
        try {
            final Client client = this.bankService.findClientByReference(reference);
            model.addAttribute("reference", reference);
            model.addAttribute("client", client);
            final List<Account> accounts = this.bankService.accountsList(client.getReference());
            model.addAttribute("accounts", accounts);
            model.addAttribute("view", "client");
            model.addAttribute("loggeduser", this.getPrincipal());
            model.addAttribute("isAdmin", this.isAdmin());
            if (error != null) {
                model.addAttribute("clientexception", error);
            }
        }
        catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "client";
    }
    
    
    @PostMapping(value={ "/addclient" })
    public String addclient(final Model model, final String name, final String email) {
    	Client client = null;
    	try {
            client = this.bankService.addClient(new Client(name, email));
        }
        catch (Exception e) {
            model.addAttribute("exception", e);
            return "client";
        }
        return "redirect:/searchclient?reference=" + client.getReference();
    }
    
    
    @PostMapping({ "/editclient" })
    public String editClient(final Model model, final Long reference, final String name, final String email) {
        try {
            final Client client = this.bankService.findClientByReference(reference);
            client.setName(name);
            client.setEmail(email);
            this.bankService.editClient(client);
        }
        catch (Exception e) {
            model.addAttribute("exception", e);
            return "redirect:/searchclient?reference=" + reference;
        }
        return "redirect:/searchclient?reference=" + reference;
    }
    
    
    @RequestMapping({ "/deleteclient" })
    public String deleteClient(final Model model, final Long reference) {
        try {
            final Client client = this.bankService.findClientByReference(reference);
            for (final Account account : client.getAccounts()) {
                this.bankService.deleteAccount(account);
            }
            this.bankService.deleteClient(client);
        }
        catch (Exception e) {
            model.addAttribute("exception", e);
            return "redirect:/searchclient?reference=" + reference;
        }
        return "redirect:/client";
    }
    
    
    @RequestMapping({ "/pdfexportclient" })
    protected ModelAndView pdfexport(final HttpServletRequest request, final HttpServletResponse response, final Long reference) throws Exception {
        final Account account = this.bankService.findAccountByReference(reference);
        return new ModelAndView(new PDFExport(), "client", account);
    }
    
    
    @RequestMapping({ "/xlsxexportclient" })
    protected ModelAndView xlsxexport(final HttpServletRequest request, final HttpServletResponse response, final Long reference) throws Exception {
        final Account account = this.bankService.findAccountByReference(reference);
        return new ModelAndView(new XLSXExport(), "client", account);
    }
    
    
    @ResponseBody
    @RequestMapping(value = { "/findclient" }, method = { RequestMethod.GET }, produces = { "application/json" })
    public Client findclient(final Long reference) {
        return this.bankService.findClientByReference(reference);
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
