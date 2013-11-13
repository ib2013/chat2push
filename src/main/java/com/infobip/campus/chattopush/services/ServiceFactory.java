package com.infobip.campus.chattopush.services;

import com.infobip.campus.chattopush.services.impl.DefaultChannelService;
import com.infobip.campus.chattopush.services.mock.ChannelServiceMock;

public class ServiceFactory {
	
	public static ChannelService getInstance(String str){
		if(str.equals("mock")){
			return new ChannelServiceMock();
		}
		else {
			return new DefaultChannelService();
		}
	}

}
