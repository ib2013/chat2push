
package com.infobip.campus.chattopush.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.infobip.campus.chattopush.models.MessageModel;

public class MessageRepository {

	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	public List<MessageModel> fetchMessagesInInterval(String un, String ch, long startTime, long endTime) {
		List<MessageModel> messages = new ArrayList<MessageModel>();
		Query query;

		query = em.createQuery("SELECT m FROM MessageModel m WHERE m.username=:un AND m.channel=:ch AND (m.messageDate > :startTime)");
		query.setParameter("un", un);
		query.setParameter("ch", ch);
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);

		messages = query.getResultList();
		return messages;
	}

	@SuppressWarnings("unchecked")
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

