package com.infobip.campus.chattopush.services.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.services.MessageService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DefaultMessageService implements MessageService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.rsstopush.channels.ChannelService#fetchChannelList()
	 */
	@Override
	public List<MessageModel> fetchMessageList() {
		List<MessageModel> messageList = MessageModel.findAllMessageModels();
		return messageList;
	}

	@Override
	public boolean addMessage(MessageModel message) {
		try {
			message.persist();
			return true; // response.getResponseCode() == 200;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public JsonArray fetchUserByChannelListService(String username) {
		
		return null;
	}


}
