package com.farouk.bengarssallah.kontu.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Set;
import com.farouk.bengarssallah.kontu.domain.UserRole;
import java.util.HashSet;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.annotation.Secured;
import java.io.IOException;
import com.farouk.bengarssallah.kontu.domain.User;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import com.farouk.bengarssallah.kontu.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import com.farouk.bengarssallah.kontu.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController
{
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    
    @Secured({ "ROLE_ADMIN", "ROLE_DBA" })
    @RequestMapping({ "/us" })
    public ModelAndView listUser(final ModelAndView model) throws IOException {
        final List<User> listUser = (List<User>)this.userService.findAll();
        model.addObject("listUser", (Object)listUser);
        model.addObject("view", (Object)"user");
        model.addObject("loggeduser", (Object)this.getPrincipal());
        model.setViewName("user");
        return model;
    }
    
    @Secured({ "ROLE_ADMIN", "ROLE_DBA" })
    @RequestMapping(value = { "/gu" }, method = { RequestMethod.GET }, produces = { "application/json" })
    @ResponseBody
    public User user(final ModelMap model, @RequestParam final Integer id) {
        return this.userService.findById((long)id);
    }
    
    @Secured({ "ROLE_ADMIN", "ROLE_DBA" })
    @RequestMapping(value = { "/au" }, method = { RequestMethod.POST })
    public String adduser(final ModelMap model, final String alg, final String aps, final String arl) {
        final User user = new User();
        user.setLogin(alg);
        final String hashed = BCrypt.hashpw(aps, BCrypt.gensalt(10));
        user.setPassword(hashed);
        final UserRole userRole = this.userRoleService.findByType(arl);
        final Set<UserRole> userroles = new HashSet<UserRole>();
        userroles.add(userRole);
        user.setUserRoles((Set)userroles);
        this.userService.addUser(user);
        return "redirect:/us";
    }
    
    @Secured({ "ROLE_ADMIN", "ROLE_DBA" })
    @RequestMapping(value = { "/eu" }, method = { RequestMethod.POST })
    public String edituser(final ModelMap model, final int id, final String elg, final String eps, final String erl) {
        final User user = this.userService.findById((long)id);
        user.setLogin(elg);
        if (eps != null && eps.length() > 0) {
            final String hashed = BCrypt.hashpw(eps, BCrypt.gensalt(10));
            user.setPassword(hashed);
        }
        final UserRole userRole = this.userRoleService.findByType(erl);
        if (!user.getUserRoles().contains(userRole)) {
            final Set<UserRole> userroles = new HashSet<UserRole>();
            userroles.add(userRole);
            user.setUserRoles((Set)userroles);
        }
        this.userService.editUser(user);
        return "redirect:/us";
    }
    
    @Secured({ "ROLE_ADMIN", "ROLE_DBA" })
    @RequestMapping(value = { "/du" }, method = { RequestMethod.GET })
    public String deleteuser(final ModelMap model, final int id, final String login, final String password, final String cfpassword, final String role) {
        this.userService.deleteUser((long)id);
        return "redirect:/us";
    }
    
    private String getPrincipal() {
        String userName = null;
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        }
        else {
            userName = principal.toString();
        }
        return userName;
    }
}

