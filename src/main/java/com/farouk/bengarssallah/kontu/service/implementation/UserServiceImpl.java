package com.farouk.bengarssallah.kontu.service.implementation;

import java.util.List;
import java.io.Serializable;
import org.springframework.transaction.annotation.Transactional;
import com.farouk.bengarssallah.kontu.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.farouk.bengarssallah.kontu.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.farouk.bengarssallah.kontu.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    @Override
    public void addUser(final User user) {
        this.userRepository.save((Object)user);
    }
    
    @Transactional
    @Override
    public void editUser(final User user) {
        this.userRepository.save((Object)user);
    }
    
    @Transactional
    @Override
    public void deleteUser(final long id) {
        this.userRepository.delete((Serializable)id);
    }
    
    @Override
    public User findById(final long id) {
        return (User)this.userRepository.findOne((Serializable)id);
    }
    
    @Override
    public User findByLogin(final String login) {
        final User user = this.userRepository.findByLogin(login);
        return user;
    }
    
    @Override
    public List<User> findAll() {
        return (List<User>)this.userRepository.findAll();
    }
}
