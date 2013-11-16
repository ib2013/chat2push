package com.infobip.campus.chattopush.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.infobip.campus.chattopush.models.MessageModel;

public class MessageRepository {

	@PersistenceContext
	protected EntityManager em;

	public List<MessageModel> fetchMessagesInInterval(String un, String ch,
			long startTime, long endTime) {

		List<MessageModel> messages = new ArrayList<MessageModel>();
		try {
			Query query;

			query = em
					.createQuery("SELECT m FROM MessageModel m WHERE m.username=:un AND m.channel=:ch AND (m.messageDate BETWEEN :startTime AND :endTime)");
			query.setParameter("un", un);
			query.setParameter("ch", ch);
			query.setParameter("startTime", startTime);
			query.setParameter("endTime", endTime);

			messages = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messages;
	}

}
