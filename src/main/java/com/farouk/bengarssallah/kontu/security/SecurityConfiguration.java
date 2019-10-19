package com.farouk.bengarssallah.kontu.security;

import org.springframework.security.config.annotation.SecurityBuilder;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
    @Autowired
    PersistentTokenRepository tokenRepository;
    
    @Autowired
    public void configureGlobalSecurity(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService);
        auth.authenticationProvider((AuthenticationProvider)this.authenticationProvider());
    }
    
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers(new String[] { "/css/**", "/images/**", "/js/**", "/fonts/**", "/video/**" });
    }
    
    protected void configure(final HttpSecurity http) throws Exception {
        ((HttpSecurity)((HttpSecurity)((HttpSecurity)((HttpSecurity)((HttpSecurity)((FormLoginConfigurer)((FormLoginConfigurer)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.authorizeRequests().antMatchers(new String[] { "/login" })).access("isAnonymous()").antMatchers(new String[] { "/**" })).access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')").and()).formLogin().loginPage("/login").loginProcessingUrl("/login")).usernameParameter("lg").passwordParameter("ps").successHandler(this.getAuthenticationSuccessHandler())).and()).rememberMe().rememberMeParameter("rm").rememberMeCookieName("krm").tokenRepository(this.tokenRepository).tokenValiditySeconds(604800).and()).sessionManagement().invalidSessionUrl("/login").and()).exceptionHandling().accessDeniedPage("/403").and()).logout().invalidateHttpSession(true).clearAuthentication(true).deleteCookies(new String[] { "JSESSIONID" }).invalidateHttpSession(false).logoutRequestMatcher((RequestMatcher)new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll().and()).csrf().disable();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return (PasswordEncoder)new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(this.userDetailsService);
        authenticationProvider.setPasswordEncoder((Object)this.passwordEncoder());
        return authenticationProvider;
    }
    
    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        final PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices("remember-me", this.userDetailsService, this.tokenRepository);
        return tokenBasedservice;
    }
    
    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return (AuthenticationTrustResolver)new AuthenticationTrustResolverImpl();
    }
    
    @Bean
    public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
        return (AuthenticationSuccessHandler)new AuthenticationSuccessHandler() {
            public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
                final HttpSession session = request.getSession(false);
                if (session != null) {
                    session.setMaxInactiveInterval(600);
                }
                response.setStatus(200);
                response.sendRedirect("/welcome");
            }
        };
    }
        

}