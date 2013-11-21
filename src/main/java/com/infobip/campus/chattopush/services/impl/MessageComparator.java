package com.infobip.campus.chattopush.services.impl;

import java.util.Comparator;

import com.infobip.campus.chattopush.models.MessageModel;

public class MessageComparator implements Comparator<MessageModel> {
	
	public int compare(MessageModel msg1, MessageModel msg2) {
        if(msg1.getMessageDate() < msg2.getMessageDate())
        	return -1;
        else if(msg1.getMessageDate() == msg2.getMessageDate())
        	return 0;
        else return 1;
    }

}
