package com.farouk.bengarssallah.kontu.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.farouk.bengarssallah.kontu.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>
{
    @Query("select c from Client c where c.reference=:x")
    Client findByReference(@Param("x") final Long p0);
}
