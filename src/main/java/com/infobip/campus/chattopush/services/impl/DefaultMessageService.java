package com.infobip.campus.chattopush.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.infobip.campus.chattopush.clients.ClientMessageModel;
import com.infobip.campus.chattopush.database.MessageRepository;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.services.MessageService;
import com.infobip.campus.chattopush.services.PushNotification;

@Service
public class DefaultMessageService implements MessageService {
	
	MessageRepository messageRepository;
	
	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Override
	public List<MessageModel> fetchMessageList(String un, String ch,
			long startTime, long endTime) {

		List<MessageModel> result = new ArrayList<MessageModel>();

		result = messageRepository.fetchMessagesInInterval(un, ch, startTime, endTime);

		Collections.sort(result, new MessageComparator());

		return result;
	}

	@Override
	public boolean sendMessage(ClientMessageModel msg) {

		MessageModel mmodel = new MessageModel();

		String username = msg.getUsername();
		String channel = msg.getChannel();
		String message = msg.getMessageText();
		long date = new Date().getTime();

		mmodel.setChannel(channel);
		mmodel.setUsername(username);
		mmodel.setMessage(message);
		mmodel.setMessageDate(date);
		
		try {
			if (addMessage(mmodel)) {
				try {
 					PushNotification pN = new PushNotification(mmodel);
					pN.notifyChannel();
				} catch (Exception e) {
					mmodel.remove();
					return false;
				}
			} else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<MessageModel> fetchAllMessages() {
		List<MessageModel> messageList = MessageModel.findAllMessageModels();
		return messageList;
	}

	@Override
	public boolean addMessage(MessageModel message) {
		try {
			message.persist();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
