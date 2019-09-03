package com.farouk.bengarssallah.kontu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.farouk.bengarssallah.kontu.domain.Account;
import com.farouk.bengarssallah.kontu.domain.Client;
import com.farouk.bengarssallah.kontu.export.PDFExport;
import com.farouk.bengarssallah.kontu.export.XLSXExport;
import com.farouk.bengarssallah.kontu.service.BankService;

@Controller
public class ClientController {
	
	  @Autowired
	  BankService bankService;

	 @RequestMapping("/client")
	  public String getClientPage(Model model){
		                  model.addAttribute("view", "client");
		                  return "client";
	  }
	 
	 
	 @RequestMapping("/searchclient")
	  public String searchClient(Model model, Long reference, String error, 
			         @RequestParam(name="page", defaultValue="0") int page,
			         @RequestParam(name="size", defaultValue="5") int size){
		                  try{
		                	    Client client = bankService.findClientByReference(reference);
		                	    model.addAttribute("reference", reference);
		                	    model.addAttribute("client", client);
		                	    List<Account> accounts = bankService.accountsList(client.getReference());
		                	    model.addAttribute("accounts", accounts);
		                	    model.addAttribute("view", "client");
		                	    if(error != null){
		                	    	       model.addAttribute("clientexception", error);
		                	    }
		                           }
		                  catch(Exception e){
		                	    model.addAttribute("exception", e);
		                  }
		                  return "client";
	         }
	 
	 
	 
	 @RequestMapping("/addclient")
	  public String addAccount(Model model, Long reference, String name, String email){
		                  try{
		                	    Client client = new Client(reference, name, email);
		                	    bankService.addClient(client);
		                           }
		                  catch(Exception e){
		                	    model.addAttribute("exception", e);
		                	    return "client";
		                  }
		                  return "redirect:/searchclient?reference=" + reference;
	        }
	 
	 
	  @RequestMapping("/editclient")
	  public String editClient(Model model, Long reference, String name, String email){
		          try{
		        	     Client client = bankService.findClientByReference(reference);
				          client.setName(name);
				          client.setEmail(email);
				          bankService.editClient(client);
                         }
		          catch(Exception e){
              	    model.addAttribute("exception", e);
              	    return "redirect:/searchclient?reference=" + reference;
                }
		          return "redirect:/searchclient?reference=" + reference;
	      }
	 
	 
	 @RequestMapping("/deleteclient")
	  public String deleteClient(Model model, Long reference){
		          try{
		        	  Client client = bankService.findClientByReference(reference);
			          for(Account account : client.getAccounts()){bankService.deleteAccount(account);}
			          bankService.deleteClient(client);
                      }
		          catch(Exception e){
              	    model.addAttribute("exception", e);
              	    return "redirect:/searchclient?reference=" + reference;
                }
		          return "redirect:/client";
	      }
	 
	 
	  @RequestMapping(value = "/pdfexportclient")
			protected ModelAndView pdfexport(HttpServletRequest request, HttpServletResponse response, Long reference) throws Exception {
				                Account account = bankService.findAccountByReference(reference);   
				                return new ModelAndView(new PDFExport(),"client",account);
		     }
			
			
			
			@RequestMapping(value = "/xlsxexportclient")
			protected ModelAndView xlsxexport(HttpServletRequest request, HttpServletResponse response, Long reference) throws Exception {
								 Account account = bankService.findAccountByReference(reference);   
					             return new ModelAndView(new XLSXExport(),"client",account);
		     }
			
			
			@ResponseBody
			@RequestMapping(value = "/findclient", method = RequestMethod.GET, produces = "application/json")
			public Client findclient(Long reference) {
				return  bankService.findClientByReference(reference);
			}
	  
}
