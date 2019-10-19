package com.farouk.bengarssallah.kontu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import com.farouk.bengarssallah.kontu.domain.PersistentLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersistentLoginRepository extends JpaRepository<PersistentLogin, String>
{
    @Query("SELECT p FROM PersistentLogin p WHERE p.series = ?1")
    PersistentLogin findBySeries(final String p0);
    
    @Query("SELECT p FROM PersistentLogin p WHERE p.username = ?1")
    List<PersistentLogin> findByUsername(final String p0);
}

