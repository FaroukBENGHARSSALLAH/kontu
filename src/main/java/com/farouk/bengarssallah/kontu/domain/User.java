package com.farouk.bengarssallah.kontu.domain;

import java.util.HashSet;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User extends AEntity implements Serializable {
	
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;
       
}
