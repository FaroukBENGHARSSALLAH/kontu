package com.farouk.bengarssallah.kontu.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import java.sql.ResultSet;
import java.util.Set;
import java.sql.Statement;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import com.farouk.bengarssallah.kontu.domain.SavingAccount;
import com.farouk.bengarssallah.kontu.domain.CheckingAccount;
import java.util.Date;
import com.farouk.bengarssallah.kontu.domain.Account;
import com.farouk.bengarssallah.kontu.domain.Client;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.RequestMapping;
import com.farouk.bengarssallah.kontu.rss.ABCNews;
import java.util.List;
import org.springframework.ui.Model;
import com.farouk.bengarssallah.kontu.service.BankService;
import com.farouk.bengarssallah.kontu.repository.UserRoleRepository;
import com.farouk.bengarssallah.kontu.repository.UserRepository;
import com.farouk.bengarssallah.kontu.repository.AccountRepository;
import com.farouk.bengarssallah.kontu.repository.ClientRepository;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import com.farouk.bengarssallah.kontu.rss.ABCRSSExtractor;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController
{
    @Autowired
    private ABCRSSExtractor aBCRSSExtractor;
    @Autowired
    private EmbeddedDatabase dataSource;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    BankService bankService;
    
    @RequestMapping({ "/", "/welcome" })
    public String getHomePage(final Model model) {
        final List<ABCNews> list = this.getSliderImages();
        model.addAttribute("list", (Object)list);
        model.addAttribute("view", (Object)"welcome");
        model.addAttribute("loggeduser", (Object)this.getPrincipal());
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
        final Client cl = (Client)this.clientRepository.save((Object)new Client(Long.valueOf(1L), "peter", "cl@gmai.com"));
        final Client cl2 = (Client)this.clientRepository.save((Object)new Client(Long.valueOf(2L), "jhon", "cl@gmai.com"));
        final Client cl3 = (Client)this.clientRepository.save((Object)new Client(Long.valueOf(3L), "dmitry", "cl@gmai.com"));
        final Account ac = (Account)this.accountRepository.save((Object)new CheckingAccount(Long.valueOf(1L), new Date(), 900.0, cl, 20.0));
        final Account ac2 = (Account)this.accountRepository.save((Object)new SavingAccount(Long.valueOf(2L), new Date(), 100.0, cl, 5.0));
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
    
    private List<ABCNews> getSliderImages() {
        final List<ABCNews> list = (List<ABCNews>)this.aBCRSSExtractor.getABCMoneyNews();
        final List<ABCNews> returnList = new LinkedList<ABCNews>();
        final Iterator<ABCNews> it = list.iterator();
        for (int i = 0; i < 25 && it.hasNext(); ++i) {
            final ABCNews news = it.next();
            if (news.getImage() != null) {
                returnList.add(news);
            }
        }
        return returnList;
    }
    
    public void clearDatabase() throws SQLException {
        final Connection c = this.dataSource.getConnection();
        final Statement s = c.createStatement();
        s.execute("SET REFERENTIAL_INTEGRITY FALSE");
        final Set<String> tables = new HashSet<String>();
        ResultSet rs = s.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC'");
        while (rs.next()) {
            final String rss = rs.getString(1);
            if (!rss.equals("USER") && !rss.equals("ROLE") && !rss.equals("KONTU_USER_ROLE") && !rss.equals("PERSISTENT_LOGIN")) {
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
}
 