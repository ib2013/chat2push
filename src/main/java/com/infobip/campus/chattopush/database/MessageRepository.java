
package com.infobip.campus.chattopush.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.services.impl.DefaultMessageService;

public class MessageRepository {

	@PersistenceContext
	protected EntityManager em;
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultMessageService.class);

	public List<MessageModel> fetchMessagesInInterval(String un, String ch, long startTime, long endTime) {
		List<MessageModel> messages = new ArrayList<MessageModel>();
		Query query;
		
		LOG.info("Fetching messages QUERY...");

		/*query = em.createQuery("SELECT m FROM MessageModel m WHERE m.channel=:ch AND (m.messageDate > :startTime)");
		query.setParameter("ch", ch);
		query.setParameter("startTime", startTime);

		messages = query.getResultList();*/
	
		List<MessageModel> allMessages = MessageModel.findAllMessageModels();
		for (MessageModel msg : allMessages){
			if (msg.getChannel().equals(ch) && msg.getMessageDate() > startTime){
				messages.add(msg);
			}
		}
		
		return messages;
	}

	public int getMessagesForUserAndChannel(String username, String channel) {
		List<MessageModel> messages = new ArrayList<MessageModel>();
		Query query;

		query = em.createQuery("SELECT m FROM MessageModel m WHERE m.username=:un AND m.channel=:ch ");
		query.setParameter("un", username);
		query.setParameter("ch", channel);

		messages = query.getResultList();
		return messages == null ? 0 : messages.size();
	}
}

