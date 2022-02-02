package com.farouk.bengarssallah.kontu.service;

import java.util.List;
import com.farouk.bengarssallah.kontu.domain.User;

public interface UserService {
	
    void saveUser(final User user);
        
    void deleteUser(final long reference);
    
    User findByReference(final long reference);
    
    User findByLogin(final String login);
    
    List<User> findAll();
}

