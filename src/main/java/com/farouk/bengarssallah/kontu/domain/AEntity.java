package com.farouk.bengarssallah.kontu.domain;

import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public class AEntity implements Serializable {
	
    @Id
    @GeneratedValue
    protected Long reference;
    @Version
    protected Integer version;
    
   
}

