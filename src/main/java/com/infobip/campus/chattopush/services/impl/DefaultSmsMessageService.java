package com.infobip.campus.chattopush.services.impl;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.SMSRequest;
import oneapi.model.SendMessageResult;

import com.infobip.campus.chattopush.services.SmsMessageService;

public class DefaultSmsMessageService implements SmsMessageService {
	Configuration configuration = null;
	SMSClient smsClient = null;

	public void init() {
		try {
			configuration = new Configuration("ccup", "pitajkonobara");
			smsClient = new SMSClient(configuration);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendSmsMessage(String from, String message, String to) {
		if (configuration != null && smsClient != null) {
			SMSRequest smsRequest = new SMSRequest(from, message, to);
			SendMessageResult sendMessageResult = smsClient
					.getSMSMessagingClient().sendSMS(smsRequest);
			 System.out.println(sendMessageResult.getSendMessageResults()[0].getMessageStatus());
		}
	}

}
