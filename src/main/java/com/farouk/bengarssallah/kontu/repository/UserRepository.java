package com.farouk.bengarssallah.kontu.repository;

import org.springframework.data.jpa.repository.Query;
import com.farouk.bengarssallah.kontu.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    @Query("SELECT u FROM User u WHERE u.login = ?1")
    User findByLogin(final String p0);
}