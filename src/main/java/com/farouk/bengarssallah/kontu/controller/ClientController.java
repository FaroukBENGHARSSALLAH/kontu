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
import java.util.Iterator;
import com.farouk.bengarssallah.kontu.domain.Account;
import java.util.List;
import com.farouk.bengarssallah.kontu.domain.Client;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.farouk.bengarssallah.kontu.service.BankService;
import org.springframework.stereotype.Controller;

@Controller
public class ClientController
{
    @Autowired
    BankService bankService;
    
    @RequestMapping({ "/client" })
    public String getClientPage(final Model model) {
        model.addAttribute("view", (Object)"client");
        model.addAttribute("loggeduser", (Object)this.getPrincipal());
        return "client";
    }
    
    @RequestMapping({ "/searchclient" })
    public String searchClient(final Model model, final Long reference, final String error, @RequestParam(name = "page", defaultValue = "0") final int page, @RequestParam(name = "size", defaultValue = "5") final int size) {
        try {
            final Client client = this.bankService.findClientByReference(reference);
            model.addAttribute("reference", (Object)reference);
            model.addAttribute("client", (Object)client);
            final List<Account> accounts = (List<Account>)this.bankService.accountsList(client.getReference());
            model.addAttribute("accounts", (Object)accounts);
            model.addAttribute("view", (Object)"client");
            if (error != null) {
                model.addAttribute("clientexception", (Object)error);
            }
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
        }
        return "client";
    }
    
    @RequestMapping({ "/addclient" })
    public String addAccount(final Model model, final Long reference, final String name, final String email) {
        try {
            final Client client = new Client(reference, name, email);
            this.bankService.addClient(client);
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
            return "client";
        }
        return "redirect:/searchclient?reference=" + reference;
    }
    
    @RequestMapping({ "/editclient" })
    public String editClient(final Model model, final Long reference, final String name, final String email) {
        try {
            final Client client = this.bankService.findClientByReference(reference);
            client.setName(name);
            client.setEmail(email);
            this.bankService.editClient(client);
        }
        catch (Exception e) {
            model.addAttribute("exception", (Object)e);
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
            model.addAttribute("exception", (Object)e);
            return "redirect:/searchclient?reference=" + reference;
        }
        return "redirect:/client";
    }
    
    @RequestMapping({ "/pdfexportclient" })
    protected ModelAndView pdfexport(final HttpServletRequest request, final HttpServletResponse response, final Long reference) throws Exception {
        final Account account = this.bankService.findAccountByReference(reference);
        return new ModelAndView((View)new PDFExport(), "client", (Object)account);
    }
    
    @RequestMapping({ "/xlsxexportclient" })
    protected ModelAndView xlsxexport(final HttpServletRequest request, final HttpServletResponse response, final Long reference) throws Exception {
        final Account account = this.bankService.findAccountByReference(reference);
        return new ModelAndView((View)new XLSXExport(), "client", (Object)account);
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
}
