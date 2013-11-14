package com.infobip.campus.chattopush.services.impl;

import com.infobip.campus.chattopush.clients.ClientMessageModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.services.MessageService;
import com.infobip.campus.chattopush.services.PushNotification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DefaultMessageService implements MessageService {

	@Override
	public List<MessageModel> fetchMessageList(String un, String ch,
			long startTime, long endTime) {
		List<MessageModel> messages = fetchAllMessages();
		List<MessageModel> result = new ArrayList<MessageModel>();
		Date date1 = new Date(startTime);
		Date date2 = new Date(endTime);

		for (MessageModel msg : messages) {
			if (msg.getChannel().equals(ch)
					&& msg.getLastMessageDate().after(date1)
					&& msg.getLastMessageDate().before(date2)) {
				result.add(msg);
			}
		}

		return result;
	}

	@Override
	public boolean sendMessage(ClientMessageModel msg) {
		MessageModel mmodel = new MessageModel();

		String username = msg.getUsername();
		String channel = msg.getChannel();
		String message = msg.getMessageText();
		Date date = new Date();

		mmodel.setChannel(channel);
		mmodel.setMessage(message);
		mmodel.setUser(username);
		mmodel.setLastMessageDate(date);

		try {
			if (addMessage(mmodel)) {
				try {
					PushNotification pN = new PushNotification(mmodel);
					pN.notifyChannel();
				} catch (Exception e) {
					List<MessageModel> msgs = fetchAllMessages();
					for (MessageModel m : msgs) {
						if (m.getChannel().equals(channel)
								&& m.getUser().equals(username)
								&& m.getMessage().equals(message)
								&& m.getLastMessageDate().equals(date)) {
							m.remove();
						}
					}
					e.printStackTrace();
					return false;
				}
			}

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
