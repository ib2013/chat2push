package com.infobip.campus.chattopush.clients;

public class UserActivityModel {

	private String username;
	private int messageCount;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}

	public UserActivityModel() {

	}
}