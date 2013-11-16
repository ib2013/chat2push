package com.infobip.campus.chattopush.services;

import com.infobip.campus.chattopush.services.impl.DefaultChannelService;
import com.infobip.campus.chattopush.services.impl.DefaultMessageService;
import com.infobip.campus.chattopush.services.impl.DefaultUserService;

public class ServiceFactory {

	public static ChannelService getChannelServiceInstance(String str) {
		
			return new DefaultChannelService();
	}

	public static MessageService getMessageServiceInstance(String str) {
			return new DefaultMessageService();
	}

	public static UserService getUserServiceInstance(String str) {
			return new DefaultUserService();
	}
}
