package com.infobip.campus.chattopush.services;

import java.util.ArrayList;

import com.infobip.campus.chattopush.models.MessageModel;

public interface MessageService {

	public abstract ArrayList<MessageModel> fetchChannelList();

//	public abstract ArrayList<ChannelModel> parseJson(String jsonResponse);

	public abstract boolean addMessage(MessageModel channel);

	public abstract boolean deleteMessage(MessageModel channel);

	public abstract boolean updateMessage(MessageModel oldModel,
			MessageModel newModel);

}