package com.infobip.campus.chattopush.services.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.infobip.campus.chattopush.clients.ClientMessageModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.services.MessageService;

public class MessageServiceMock implements MessageService {

	List<MessageModel> result = new ArrayList<MessageModel>();
	
	public MessageServiceMock(){
		MessageModel m1 = new MessageModel();
		m1.setChannel("kanal1");
		m1.setMessage("poruka broj1");
		m1.setUser("korisnik1");
		m1.setLastMessageDate(new Date());
		result.add(m1);
		
		MessageModel m4 = new MessageModel();
		m4.setChannel("kanal2");
		m4.setMessage("poruka broj4");
		m4.setUser("korisnik1");
		m4.setLastMessageDate(new Date());
		result.add(m4);
		
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
	public List<MessageModel> fetchMessageList(String un, String ch, long startTime, long endTime) {
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

	@Override
	public List<MessageModel> fetchAllMessages() {
		return result;
	}

	@Override
	public boolean sendMessage(ClientMessageModel message) {
		MessageModel mmodel = new MessageModel();

		String username = message.getUsername();
		String channel = message.getChannel();
		String msg = message.getMessageText();
		Date date = new Date();
		
		mmodel.setChannel(channel);
		mmodel.setMessage(msg);
		mmodel.setUser(username);
		mmodel.setLastMessageDate(date);
		
		if(addMessage(mmodel) == true){
			result.add(mmodel);
			return true;
		}
		else 
			return false;
	}

}
