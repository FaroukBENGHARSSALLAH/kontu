package com.farouk.bengarssallah.kontu.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import com.farouk.bengarssallah.kontu.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        final com.farouk.bengarssallah.kontu.domain.User user = this.userService.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new User(user.getLogin(), user.getPassword(), true, true, true, true, this.getGrantedAuthorities(user));
    }
    
    private List<GrantedAuthority> getGrantedAuthorities(final com.farouk.bengarssallah.kontu.domain.User user) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }
}

