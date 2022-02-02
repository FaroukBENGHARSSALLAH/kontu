package com.farouk.bengarssallah.kontu.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class SecurityController {
    
    private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
    private AuthenticationTrustResolver authenticationTrustResolver;
    
    @RequestMapping(value = { "/login" }, method = { RequestMethod.GET })
    public String loginPage() {
        if (this.isCurrentAuthenticationAnonymous()) {
            return "login";
        }
        return "redirect:/";
    }
    
    @RequestMapping(value = { "/logout" }, method = { RequestMethod.GET })
    public String logoutPage(final HttpServletRequest request, final HttpServletResponse response) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            this.persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication((Authentication)null);
        }
        return "redirect:/login?logout";
    }
    
    @RequestMapping(value = { "/403" }, method = { RequestMethod.GET })
    public ModelAndView accesssDenied() {
        final ModelAndView model = new ModelAndView();
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            final UserDetails userDetail = (UserDetails)auth.getPrincipal();
            model.addObject("username", (Object)userDetail.getUsername());
        }
        model.setViewName("403");
        return model;
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
    
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.authenticationTrustResolver.isAnonymous(authentication);
    }
}
