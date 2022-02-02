package com.farouk.bengarssallah.kontu.domain;

import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "persistent_login")
@Getter
@Setter
public class PersistentLogin implements Serializable {
	
    @Id
    private String series;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "token", unique = true, nullable = false)
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_used;
    
}
