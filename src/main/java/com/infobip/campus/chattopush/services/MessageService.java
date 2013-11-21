package com.infobip.campus.chattopush.services;

import java.util.List;

import com.infobip.campus.chattopush.clients.ClientMessageModel;
import com.infobip.campus.chattopush.models.MessageModel;

public interface MessageService {

	public List<MessageModel> fetchMessageList(String un, String ch, long startTime, long endTime);
	
	public List<MessageModel> fetchAllMessages();

	public boolean addMessage(MessageModel message);
	
	public boolean sendMessage(ClientMessageModel message);

}