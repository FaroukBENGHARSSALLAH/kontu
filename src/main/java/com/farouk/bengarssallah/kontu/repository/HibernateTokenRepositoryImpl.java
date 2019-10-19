package com.farouk.bengarssallah.kontu.repository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.farouk.bengarssallah.kontu.domain.PersistentLogin;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl implements PersistentTokenRepository
{
    @Resource
    private PersistentLoginRepository persistentLoginRepository;
    
    public void createNewToken(final PersistentRememberMeToken token) {
        final PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        this.persistentLoginRepository.save((Object)persistentLogin);
    }
    
    public PersistentRememberMeToken getTokenForSeries(final String seriesId) {
        final PersistentLogin persistentLogin = this.persistentLoginRepository.findBySeries(seriesId);
        return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(), persistentLogin.getToken(), persistentLogin.getLast_used());
    }
    
    public void removeUserTokens(final String username) {
        final List<PersistentLogin> persistentLogins = (List<PersistentLogin>)this.persistentLoginRepository.findByUsername(username);
        if (persistentLogins.size() > 1) {
            for (final PersistentLogin p : persistentLogins) {
                this.persistentLoginRepository.delete((Object)p);
            }
        }
        else if (persistentLogins.size() == 1) {
            this.persistentLoginRepository.delete((Object)persistentLogins.get(0));
        }
    }
    
    public void updateToken(final String seriesId, final String tokenValue, final Date lastUsed) {
        final PersistentLogin persistentLogin = this.persistentLoginRepository.findBySeries(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);
        this.persistentLoginRepository.save((Object)persistentLogin);
    }
}

