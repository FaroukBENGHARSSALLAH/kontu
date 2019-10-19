package com.farouk.bengarssallah.kontu.domain;

import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "persistent_login")
public class PersistentLogin implements Serializable
{
    @Id
    private String series;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "token", unique = true, nullable = false)
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_used;
    
    public String getSeries() {
        return this.series;
    }
    
    public void setSeries(final String series) {
        this.series = series;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getToken() {
        return this.token;
    }
    
    public void setToken(final String token) {
        this.token = token;
    }
    
    public Date getLast_used() {
        return this.last_used;
    }
    
    public void setLast_used(final Date last_used) {
        this.last_used = last_used;
    }
}
