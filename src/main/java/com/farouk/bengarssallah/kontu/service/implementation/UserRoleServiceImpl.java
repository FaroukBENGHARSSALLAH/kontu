package com.farouk.bengarssallah.kontu.service.implementation;

import java.util.List;
import java.io.Serializable;
import com.farouk.bengarssallah.kontu.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import com.farouk.bengarssallah.kontu.repository.UserRoleRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.farouk.bengarssallah.kontu.service.UserRoleService;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService
{
    @Autowired
    UserRoleRepository userRoleRepository;
    
    @Override
    public UserRole findById(final long id) {
        return (UserRole)this.userRoleRepository.findOne((Serializable)id);
    }
    
    @Override
    public UserRole findByType(final String type) {
        return this.userRoleRepository.findByType(type);
    }
    
    @Override
    public List<UserRole> findAll() {
        return (List<UserRole>)this.userRoleRepository.findAll();
    }
}
