package com.infobip.campus.chattopush.models;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.datanucleus.store.types.sco.simple.ArrayList;
import org.datanucleus.store.types.sco.simple.List;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Entity
public class UserModel {

	public UserModel() {
	}

	public UserModel(String username, String password) {
		this.username = password;
		this.password = password;
	}

	public UserModel(String username) {
		this.username = username;
	}

	/**
     */
	private String googleId = new String();

	/**
     */
	private String username;

	/**
     */
	private String password;

	
     
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
	private Set<ChannelModel> channels = new HashSet<ChannelModel>();
	
}
