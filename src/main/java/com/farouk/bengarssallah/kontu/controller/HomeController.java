package com.farouk.bengarssallah.kontu.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.farouk.bengarssallah.kontu.domain.Account;
import com.farouk.bengarssallah.kontu.domain.CheckingAccount;
import com.farouk.bengarssallah.kontu.domain.Client;
import com.farouk.bengarssallah.kontu.domain.SavingAccount;
import com.farouk.bengarssallah.kontu.repository.AccountRepository;
import com.farouk.bengarssallah.kontu.repository.ClientRepository;
import com.farouk.bengarssallah.kontu.rss.ABCNews;
import com.farouk.bengarssallah.kontu.rss.ABCRSSExtractor;
import com.farouk.bengarssallah.kontu.service.BankService;

@Controller
public class HomeController {

	  
	    @Autowired
	    private ABCRSSExtractor aBCRSSExtractor;
	    
	    @Autowired
	    private EmbeddedDatabase dataSource;
	    
		@Autowired
		private ClientRepository clientRepository;
		
		@Autowired
		private AccountRepository accountRepository;
	    
	    @Autowired
		BankService bankService;
	    
	  
	 @RequestMapping(value={"/", "/welcome"})
	  public String getHomePage(Model model){
		                  List<ABCNews> list = getSliderImages();
		                  model.addAttribute("list", list);
		                  model.addAttribute("view", "welcome");
		                  reset();
		                  return "welcome";
	  }
	 
	  
		private void reset() {
			try {
				clearDatabase();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Client cl = clientRepository.save(new Client(1L, "peter", "cl@gmai.com"));
			Client cl2 = clientRepository.save(new Client(2L, "jhon", "cl@gmai.com"));
			Client cl3 = clientRepository.save(new Client(3L, "dmitry", "cl@gmai.com"));
			Account ac = accountRepository.save(new CheckingAccount(1L, new Date(), 900, cl, 20));
			Account ac2 = accountRepository.save(new SavingAccount(2L, new Date(), 100, cl, 5));
			bankService.pay(ac.getReference(), 2);
			bankService.pay(ac.getReference(), 2);
			bankService.pay(ac.getReference(), 2);
			bankService.pay(ac.getReference(), 2);
			bankService.pay(ac.getReference(), 2);
			bankService.pay(ac.getReference(), 2);
			bankService.pay(ac.getReference(), 2);
			bankService.pay(ac.getReference(), 2);
			bankService.pay(ac.getReference(), 2);
			bankService.pay(ac.getReference(), 2);
			bankService.pay(ac.getReference(), 2);
			bankService.pay(ac.getReference(), 2);
			bankService.withdraw(ac.getReference(), 5);
			bankService.trasnfert(ac.getReference(), ac2.getReference(), 20);
	}


		private List<ABCNews> getSliderImages() {
			            List<ABCNews> list = aBCRSSExtractor.getABCMoneyNews();
			            List<ABCNews> returnList = new  LinkedList<ABCNews>();
			            Iterator<ABCNews> it = list.iterator();
			            int i =0;
			            while(i<25 && it.hasNext()){
			         	                  ABCNews news = (ABCNews) it.next();
			         	                  if(news.getImage() != null){
			         	                	                    returnList.add(news);
			         	                	                    i++;
			         	                                 }
			                          }
			            return returnList;
            }
		
		
		
		
		public void clearDatabase() throws SQLException {
		    Connection c = dataSource.getConnection();
		    Statement s = c.createStatement();
		    s.execute("SET REFERENTIAL_INTEGRITY FALSE");
		    // Find all tables and truncate them
		    Set<String> tables = new HashSet<String>();
		    ResultSet rs = s.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  where TABLE_SCHEMA='PUBLIC'");
		    while (rs.next()) {
		        tables.add(rs.getString(1));
		    }
		    rs.close();
		    for (String table : tables) {
		        s.executeUpdate("TRUNCATE TABLE " + table);
		    }
		    // Idem for sequences
		    Set<String> sequences = new HashSet<String>();
		    rs = s.executeQuery("SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SEQUENCES WHERE SEQUENCE_SCHEMA='PUBLIC'");
		    while (rs.next()) {
		        sequences.add(rs.getString(1));
		    }
		    rs.close();
		    for (String seq : sequences) {
		        s.executeUpdate("ALTER SEQUENCE " + seq + " RESTART WITH 1");
		    }
		    // Enable FK
		    s.execute("SET REFERENTIAL_INTEGRITY TRUE");
		    s.close();
		}
	  

	  
}
