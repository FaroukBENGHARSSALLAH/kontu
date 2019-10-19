package com.farouk.bengarssallah.kontu.domain;

@Entity
@Table(name = "role")
public class UserRole extends AEntity implements Serializable
{
    @Column(name = "type", unique = true, nullable = false)
    private String type;
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
}