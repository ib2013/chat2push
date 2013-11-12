package com.infobip.campus.chattopush.services;


import com.infobip.campus.chattopush.models.MessageModel;

import java.net.URL;
import java.util.ArrayList;

import org.springframework.stereotype.Service;


@SuppressWarnings("deprecation")
@Service
public class DefaultMessageService implements MessageService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.rsstopush.channels.ChannelService#fetchChannelList()
	 */
	@Override
	public ArrayList<MessageModel> fetchMessageList() {
		ArrayList<MessageModel> messageList = new ArrayList<MessageModel>(MessageModel.findAllMessageModels());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.rsstopush.channels.ChannelService#deleteChannel(com
	 * .infobip.campus.rsstopush.channels.ChannelModel)
	 */
	@Override
	public boolean deleteMessage(MessageModel message) {
		try {
			// OVO TREBA SKONTAT KAKO CEMO
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.rsstopush.channels.ChannelService#updateChannel(com
	 * .infobip.campus.rsstopush.channels.ChannelModel,
	 * com.infobip.campus.rsstopush.channels.ChannelModel)
	 */
	@Override
	public boolean updateMessage(MessageModel oldModel, MessageModel newModel) {

		return true;
	}
}
