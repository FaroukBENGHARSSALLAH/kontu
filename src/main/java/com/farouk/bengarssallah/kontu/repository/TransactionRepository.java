package com.farouk.bengarssallah.kontu.repository;

import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.repository.query.Param;
import com.farouk.bengarssallah.kontu.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t where t.account.reference=:x order by t.date desc")
    List<Transaction> transactionList(@Param("x") final Long p0);
}