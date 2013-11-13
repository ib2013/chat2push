package com.infobip.campus.chattopush.services.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.services.MessageService;
import com.infobip.campus.chattopush.services.PushNotification;

public class MessageServiceMock implements MessageService {

	@Override
	public List<MessageModel> fetchMessageList() {
		List<MessageModel> result = new ArrayList<MessageModel>();
		
		MessageModel m1 = new MessageModel();
		m1.setChannel("kanal 1");
		m1.setMessage("poruka broj 1");
		m1.setUser("korisnik 1");
		m1.setLastMessageDate(new Date());
		result.add(m1);
		
		MessageModel m12 = new MessageModel();
		m1.setChannel("kanal 2");
		m1.setMessage("poruka broj 2");
		m1.setUser("korisnik 2");
		m1.setLastMessageDate(new Date());
		result.add(m12);
		
		MessageModel m3 = new MessageModel();
		m1.setChannel("kanal 3");
		m1.setMessage("poruka broj 3");
		m1.setUser("korisnik 3");
		m1.setLastMessageDate(new Date());
		result.add(m3);
		
		return result;
	}

	@Override
	public boolean addMessage(MessageModel message) {
		try{
			PushNotification pN = new PushNotification(message);
			pN.notifyChannel();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
