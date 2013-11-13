package com.infobip.campus.chattopush.services.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.services.MessageService;
import com.infobip.campus.chattopush.services.PushNotification;

public class MessageServiceMock implements MessageService {

	List<MessageModel> result = new ArrayList<MessageModel>();
	
	public MessageServiceMock(){
		MessageModel m1 = new MessageModel();
		m1.setChannel("kanal1");
		m1.setMessage("poruka broj1");
		m1.setUser("korisnik1");
		m1.setLastMessageDate(new Date());
		result.add(m1);
		
		MessageModel m2 = new MessageModel();
		m2.setChannel("kanal2");
		m2.setMessage("poruka broj2");
		m2.setUser("korisnik2");
		m2.setLastMessageDate(new Date());
		result.add(m2);
		
		MessageModel m3 = new MessageModel();
		m3.setChannel("kanal3");
		m3.setMessage("poruka broj3");
		m3.setUser("korisnik3");
		m3.setLastMessageDate(new Date());
		result.add(m3);
	}
	
	@Override
	public List<MessageModel> fetchMessageList() {
		return result;
	}

	@Override
	public boolean addMessage(MessageModel message) {
		try{
			result.add(message);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
