package com.farouk.bengarssallah.kontu.controller;

import java.util.Date;
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
import com.farouk.bengarssallah.kontu.domain.CheckingAccount;
import com.farouk.bengarssallah.kontu.domain.Client;
import com.farouk.bengarssallah.kontu.domain.SavingAccount;
import com.farouk.bengarssallah.kontu.domain.Transaction;
import com.farouk.bengarssallah.kontu.export.PDFExport;
import com.farouk.bengarssallah.kontu.export.XLSXExport;
import com.farouk.bengarssallah.kontu.service.BankService;

@Controller
public class AccountController {
	
	
	   @Autowired
	  BankService bankService;
	

	@RequestMapping("/account")
	  public String getAccountPage(Model model){
		                  model.addAttribute("clientlist", bankService.findAllClient());
		                  model.addAttribute("view", "account");
		                  return "account";
	  }
	
	
	
	 @RequestMapping("/searchaccount")
	  public String searchAccount(Model model, Long reference, String error, 
			         @RequestParam(name="page", defaultValue="0") int page,
			         @RequestParam(name="size", defaultValue="5") int size){
		                  try{
		                	    Account account = bankService.findAccountByReference(reference);
		                	    model.addAttribute("reference", reference);
		                	    model.addAttribute("view", "account");
		                	    model.addAttribute("account", account);
		                	    model.addAttribute("clientlist", bankService.findAllClient());
		                	    List<Transaction> transactions = bankService.transactionsList(account.getReference());
		                	    model.addAttribute("transactions", transactions);
		                	    if(error != null){
		                	    	       model.addAttribute("transactionexception", error);
		                	    }
		                           }
		                  catch(Exception e){
		                	    model.addAttribute("exception", e);
		                  }
		                  return "account";
	         }
	 
	 
	 
	 @RequestMapping("/addaccount")
	  public String addAccount(Model model, Long reference, double balance,
			                                String type, 
			                                @RequestParam(name="overdraft", defaultValue="0") double overdraft, 
			                                @RequestParam(name="interest", defaultValue="0") double interest, Long clientref){
		                  try{
		                	    Client client = bankService.findClientByReference(clientref);
		                	    if(type.equals("checking")){
		                	    	bankService.addAccount(new CheckingAccount(reference,
		                	    			new Date(), balance, client, overdraft));
		                	             }
		                	    else  if(type.equals("saving")){
		                	    	bankService.addAccount(new SavingAccount(reference,
		                	    			new Date(), balance, client, interest));
		                	             }
		                           }
		                  catch(Exception e){
		                	        model.addAttribute("exception", e);
		                	       return "redirect:/account";
		                  }
		                  return "redirect:/searchaccount?reference=" + reference;
	        }
	 
	 

	  @RequestMapping("/editaccount")
	  public String editClient(Model model, Long reference, double balance,
					              String type, double overdraft, double interest,
					              Long clientref){
					  try{
			      	    Client client = bankService.findClientByReference(clientref);
			      	    Account account = bankService.findAccountByReference(reference);
			      	    account.setBalance(balance);
			      	    if(type.equals("checking")){
			      	    	 ((CheckingAccount) account).setOverdraft(overdraft);
			      	    	bankService.editAccount(new CheckingAccount(reference,
			      	    			new Date(), balance, client, overdraft));
			      	             }
			      	    else  if(type.equals("saving")){
			      	    	((SavingAccount) account).setInterest(interest);
			      	    	bankService.editAccount(new SavingAccount(reference,
			      	    			new Date(), balance, client, interest));
			      	             }
			                 }
			        catch(Exception e){
			        	    model.addAttribute("exception", e);
			      	       return "redirect:/searchaccount?reference=" + reference + "&&error=" + e.getMessage();
			           }
			        return "redirect:/searchaccount?reference=" + reference;
	      }
	 
	 
	 @RequestMapping("/submittransaction")
	  public String submitTransaction(Model model, String type, double amount,
			                                   Long reference, Long targetaccreference ){
		                  try{
		                	    if(type.equals("payment")){
		                	    	bankService.pay(reference, amount);
		                	            }
		                	    else if(type.equals("withdraw")){
		                	    	bankService.withdraw(reference, amount);
              	                        }
		                	    if(type.equals("transfert")){
		                	    	bankService.trasnfert(reference, targetaccreference, amount);
              	                       }
		                           }
		                  catch(Exception e){
		                	       return "redirect:/searchaccount?reference=" + reference + "&&error=" + e.getMessage();
		                  }
		                  return "redirect:/searchaccount?reference=" + reference;
	  }  
	 
	  
	  @RequestMapping(value = "/pdfexportaccount")
		protected ModelAndView pdfexport(HttpServletRequest request, HttpServletResponse response, Long reference) throws Exception {
			                Account account = bankService.findAccountByReference(reference);   
			                return new ModelAndView(new PDFExport(),"account",account);
	     }
		
		
		
		@RequestMapping(value = "/xlsxexportaccount")
		protected ModelAndView xlsxexport(HttpServletRequest request, HttpServletResponse response, Long reference) throws Exception {
							 Account account = bankService.findAccountByReference(reference);   
				             return new ModelAndView(new XLSXExport(),"account",account);
	     }
	 
	 
	 
	  @RequestMapping("/deleteaccount")
	  public String deleteAccount(Model model, Long reference){
		                    try{
		                    	Account account = bankService.findAccountByReference(reference);
			                    bankService.deleteAccount(account);
		                        }
		  		          catch(Exception e){
		                	    model.addAttribute("exception", e);
		                	    return "redirect:/searchaccount?reference=" + reference;
		                  }
		  		          return "redirect:/account";
	      }
	  
	  
	  
	    @ResponseBody
		@RequestMapping(value = "/findaccount", method = RequestMethod.GET, produces = "application/json")
		public Account findaccount(Long reference) {
			return  bankService.findAccountByReference(reference);
		}
	  }
