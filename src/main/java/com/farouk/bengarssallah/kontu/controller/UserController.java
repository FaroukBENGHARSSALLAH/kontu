package com.farouk.bengarssallah.kontu.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.annotation.Secured;
import java.io.IOException;
import com.farouk.bengarssallah.kontu.domain.User;

import java.util.Collection;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import com.farouk.bengarssallah.kontu.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class UserController {
	
    private UserService userService;
    
    @Secured({ "ROLE_ADMIN" })
    @RequestMapping({ "/user" })
    public ModelAndView listUser(final ModelAndView model) throws IOException {
        final List<User> listUser = this.userService.findAll();
        model.addObject("users", listUser);
        model.addObject("view", "user");
        model.addObject("loggeduser", this.getPrincipal());
        model.addObject("isAdmin", this.isAdmin());
        model.setViewName("employees");
        return model;
    }
    

    
    @Secured({ "ROLE_ADMIN" })
    @PostMapping({ "/senduser" })
    public String sendUser(final ModelMap model, final Long reference, final String login, final String password, final String role) {
    	User user = null;
    	if(reference == -1)
    	    user = new User();
        else user = userService.findByReference(reference);
        user.setLogin(login);
        user.setRole(role);
        if(password.length() > 0) {
             final String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
             user.setPassword(hashed);
            }
        this.userService.saveUser(user);
        return "redirect:/user";
    }
    
       @Secured({ "ROLE_ADMIN" })
    @RequestMapping(value = { "/deleteuser" }, method = { RequestMethod.GET })
    public String deleteuser(final ModelMap model, final long id) {
        this.userService.deleteUser(id);
        return "redirect:/user";
    }
    
    private String getPrincipal() {
        String userName = null;
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        }
        else {
            userName = principal.toString();
        }
        return userName;
    }
    
    
    private boolean isAdmin() {
    	final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            final Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();
        	for(GrantedAuthority authority : authorities) {
        	      if(authority.getAuthority().equals("ROLE_ADMIN"))	
        	    	   return true;
        	 }
        }
        else {
            return false;
        }
		return false;
    }
    
}

