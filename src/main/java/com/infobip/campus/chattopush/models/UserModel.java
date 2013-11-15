package com.infobip.campus.chattopush.models;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class UserModel {

	/**
     */
	private String username;

	/**
     */
	private String password;

	/*
	 * Constructors
	 */
	public UserModel() {
	}

	public UserModel(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public UserModel(String username, String password, String phoneNumber) {
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public UserModel(String username, int registrationCode) {
		this.username = username;
		this.registrationCode = registrationCode;
	}

	public UserModel(String username) {
		this.username = username;
	}

	private int registrationStatus;

	private int registrationCode;

	private String phoneNumber;

	public int getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(int registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public int getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(int registrationCode) {
		this.registrationCode = registrationCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
