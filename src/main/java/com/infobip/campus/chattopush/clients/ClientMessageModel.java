package com.infobip.campus.chattopush.clients;

public class ClientMessageModel {
	private String username;
	private String channel;
	private String messageText;
	
	public ClientMessageModel(){
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

}
