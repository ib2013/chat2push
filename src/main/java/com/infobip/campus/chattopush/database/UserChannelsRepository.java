package com.infobip.campus.chattopush.database;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.infobip.campus.chattopush.models.UsersChannels;

public class UserChannelsRepository {

	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	public Collection<UsersChannels> getSubscribedChannels(String username) {
		Query query = em
				.createQuery("SELECT uc FROM UsersChannels uc WHERE uc.username = :username");
		query.setParameter("username", username);

		return query.getResultList();
	}

}
