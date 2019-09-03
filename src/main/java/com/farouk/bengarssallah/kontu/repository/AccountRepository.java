package com.farouk.bengarssallah.kontu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farouk.bengarssallah.kontu.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	  @Query("select a from Account a where a.reference=:x")
      public Account findByReference(@Param("x") Long reference);
	  
	  @Query("select a from Account a where a.client.reference=:x order by a.creationDate desc")
      public List<Account> accountList(@Param("x") Long reference);
}
