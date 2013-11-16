package com.infobip.campus.chattopush.services;

import com.infobip.campus.chattopush.services.impl.DefaultChannelService;
import com.infobip.campus.chattopush.services.impl.DefaultMessageService;
import com.infobip.campus.chattopush.services.impl.DefaultUserService;

import com.infobip.campus.chattopush.services.mock.MessageServiceMock;

public class ServiceFactory {

	public static ChannelService getChannelServiceInstance(String str) {
		if (str.equals("mock")) {
			return null;
		} else {
			return new DefaultChannelService();
		}
	}

	public static MessageService getMessageServiceInstance(String str) {
		if (str.equals("mock")) {
			return new MessageServiceMock();
		} else {
			return new DefaultMessageService();
		}
	}

	public static UserService getUserServiceInstance(String str) {
		if (str.equals("mock")) {
			return null;
		} else {
			return new DefaultUserService();
		}
	}
}
