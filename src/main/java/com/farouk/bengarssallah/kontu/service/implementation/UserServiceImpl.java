package com.farouk.bengarssallah.kontu.service.implementation;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.farouk.bengarssallah.kontu.domain.User;

import com.farouk.bengarssallah.kontu.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.farouk.bengarssallah.kontu.service.UserService;

import lombok.AllArgsConstructor;

@Service("userService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {
   
    private UserRepository userRepository;
    
    @Transactional
    @Override
    public void saveUser(final User user) {
        this.userRepository.save(user);
    }
    
    @Transactional
    @Override
    public void deleteUser(final long reference) {
        this.userRepository.delete(reference);
    }
    
    @Override
    public User findByReference(final long reference) {
        return this.userRepository.findOne(reference);
    }
    
    @Override
    public User findByLogin(final String login) {
        final User user = this.userRepository.findByLogin(login);
        return user;
    }
    
    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
