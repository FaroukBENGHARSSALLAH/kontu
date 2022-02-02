package com.farouk.bengarssallah.kontu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.farouk.bengarssallah.kontu.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a where a.reference=:x")
    Account findByReference(@Param("x") final Long p0);
    
    @Query("select a from Account a where a.client.reference=:x order by a.creationDate desc")
    List<Account> accountList(@Param("x") final Long p0);
}
