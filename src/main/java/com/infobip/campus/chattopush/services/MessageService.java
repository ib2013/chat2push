package com.infobip.campus.chattopush.services;

import java.util.List;

import com.infobip.campus.chattopush.models.MessageModel;

public interface MessageService {

	public List<MessageModel> fetchMessageList();

//	public abstract ArrayList<ChannelModel> parseJson(String jsonResponse);

	public boolean addMessage(MessageModel message);

}