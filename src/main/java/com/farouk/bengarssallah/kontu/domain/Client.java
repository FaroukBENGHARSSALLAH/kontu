package com.farouk.bengarssallah.kontu.domain;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client extends AEntity implements Serializable {
	
    private String name;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private Collection<Account> accounts;
   
    
    public Client(final String name, final String email) {
        this.name = name;
        this.email = email;
    }
    
   
    
   
}
