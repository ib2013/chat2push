package com.infobip.campus.chattopush.services;

import com.infobip.campus.chattopush.models.MessageModel;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.rsstopush.channels.ChannelService#parseJson(java.lang
	 * .String)
	 */
	/*@Override
	public ArrayList<ChannelModel> parseJson(String jsonResponse) {

	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.rsstopush.channels.ChannelService#addChannel(com.infobip
	 * .campus.rsstopush.channels.ChannelModel)
	 */
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

}
