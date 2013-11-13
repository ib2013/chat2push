package com.infobip.campus.chattopush.services;

import com.infobip.campus.chattopush.services.impl.DefaultChannelService;
import com.infobip.campus.chattopush.services.impl.DefaultUserService;
import com.infobip.campus.chattopush.services.mock.ChannelServiceMock;
import com.infobip.campus.chattopush.services.mock.UserServiceMock;

public class ServiceFactory {

	public static ChannelService getInstance(String str) {
		if (str.equals("mock")) {
			return new ChannelServiceMock();
		} else {
			return new DefaultChannelService();
		}
	}

	public static UserService getUserServiceInstance(String str) {
		if (str.equals("mock")) {
			return new UserServiceMock();
		} else {
			return new DefaultUserService();
		}
	}
}
