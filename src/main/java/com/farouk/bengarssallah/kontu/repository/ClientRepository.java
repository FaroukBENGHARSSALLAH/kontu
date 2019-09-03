package com.farouk.bengarssallah.kontu.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farouk.bengarssallah.kontu.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	  
	  @Query("select c from Client c where c.reference=:x")
      public Client findByReference(@Param("x") Long reference);
}
