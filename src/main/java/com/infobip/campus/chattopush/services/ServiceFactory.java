package com.infobip.campus.chattopush.services;

import com.infobip.campus.chattopush.services.impl.DefaultChannelService;
import com.infobip.campus.chattopush.services.impl.DefaultMessageService;
import com.infobip.campus.chattopush.services.mock.ChannelServiceMock;
import com.infobip.campus.chattopush.services.mock.MessageServiceMock;

public class ServiceFactory {
	
	public static ChannelService getInstance(String str){
		if(str.equals("mock")){
			return new ChannelServiceMock();
		}
		else {
			return new DefaultChannelService();
		}
	}
	
	public static MessageService getMessageServiceInstance(String str){
		if(str.equals("mock")){
			return new MessageServiceMock();
		}
		else {
			return new DefaultMessageService();
		}
	}

}
