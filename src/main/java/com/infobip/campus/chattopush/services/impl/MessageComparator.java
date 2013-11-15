package com.infobip.campus.chattopush.services.impl;

import java.util.Comparator;

import com.infobip.campus.chattopush.models.MessageModel;

public class MessageComparator implements Comparator<MessageModel> {
	
	public int compare(MessageModel msg1, MessageModel msg2) {
        return msg1.getLastMessageDate().compareTo(msg2.getLastMessageDate());
    }

}
