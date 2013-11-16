package com.infobip.campus.chattopush.services;

public interface SmsMessageService {
	
	public void sendSmsMessage(String from, String message, String to);

}
