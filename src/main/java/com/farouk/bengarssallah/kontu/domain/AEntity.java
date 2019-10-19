package com.farouk.bengarssallah.kontu.domain;

import javax.persistence.Version;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class AEntity implements Serializable
{
    @Id
    @GeneratedValue
    protected Long code;
    @Version
    protected Integer version;
    
    public Long getCode() {
        return this.code;
    }
    
    public void setCode(final Long code) {
        this.code = code;
    }
    
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(final Integer version) {
        this.version = version;
    }
}

