package com.farouk.bengarssallah.kontu.repository;

import org.springframework.data.jpa.repository.Query;
import com.farouk.bengarssallah.kontu.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>
{
    @Query("SELECT u FROM UserRole u WHERE u.type = ?1")
    UserRole findByType(final String p0);
}
