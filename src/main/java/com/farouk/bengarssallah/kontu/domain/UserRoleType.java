package com.farouk.bengarssallah.kontu.domain;

import java.io.Serializable;

public enum UserRoleType implements Serializable
{
    USER("USER"), 
    DBA("DBA"), 
    ADMIN("ADMIN");
    
    String userProfileType;
    
    private UserRoleType(final String userProfileType) {
        this.userProfileType = userProfileType;
    }
    
    public String getUserProfileType() {
        return this.userProfileType;
    }
}