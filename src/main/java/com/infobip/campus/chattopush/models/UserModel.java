package com.infobip.campus.chattopush.models;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord

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
	private String username;

	/**
     */
	private String password;

}
