package com.farouk.bengarssallah.kontu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farouk.bengarssallah.kontu.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
		
	 
	       @Query("select t from Transaction t where t.account.code=:x order by t.date desc")
	       public List<Transaction> transactionList(@Param("x") Long code);
}
