package com.farouk.bengarssallah.kontu.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "persistent_logins")
public class PersistentLogin {

		   @Id
		   private String series;
		   private String username;
		   private String token;
		   private Date last_used;
		   
	
}
