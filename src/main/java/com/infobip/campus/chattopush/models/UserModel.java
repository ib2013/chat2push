package com.infobip.campus.chattopush.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

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
	private String googleId;

	/**
     */
	private String username;

	/**
     */
	private String password;

	
     
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
	private List<ChannelModel> channels = new ArrayList<ChannelModel>();
	
}
