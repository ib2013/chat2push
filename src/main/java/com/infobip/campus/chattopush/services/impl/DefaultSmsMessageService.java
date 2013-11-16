package com.infobip.campus.chattopush.services.impl;

//import oneapi.client.impl.SMSClient;
//import oneapi.config.Configuration;
//import oneapi.model.SMSRequest;
//import oneapi.model.SendMessageResult;

import com.infobip.campus.chattopush.services.SmsMessageService;

public class DefaultSmsMessageService implements SmsMessageService {
	//Configuration configuration = new Configuration("ccup","pitajkonobara");
	//SMSClient smsClient = new SMSClient(configuration);
	
	@Override
	public void sendSmsMessage(String from, String message, String to) {
		//SMSRequest smsRequest = new SMSRequest(from, message, to);
		//SendMessageResult sendMessageResult = smsClient.getSMSMessagingClient().sendSMS(smsRequest);
	}

}
