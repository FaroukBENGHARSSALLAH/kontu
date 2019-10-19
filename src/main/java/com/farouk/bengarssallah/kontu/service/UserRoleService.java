package com.farouk.bengarssallah.kontu.service;

import java.util.List;
import com.farouk.bengarssallah.kontu.domain.UserRole;

public interface UserRoleService
{
    UserRole findById(final long p0);
    
    UserRole findByType(final String p0);
    
    List<UserRole> findAll();
}