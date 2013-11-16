package com.infobip.campus.chattopush.database;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.infobip.campus.chattopush.models.UserModel;

public class UserRepository {

	@PersistenceContext
	protected EntityManager em;

	public UserModel findByUsername(String username) {
		Query query = em.createQuery("SELECT u from UserModel u where u.username = :username");
		query.setParameter("username", username);
		return (UserModel) query.getSingleResult();
	}

	public void deleteUser(String username) {
		Query query = em.createQuery("DELETE FROM UserModel u where u.username = :username");
		query.setParameter("username", username);
		query.executeUpdate();
	}

	public void removeFromAllChannels(String username) {
		Query query = em.createQuery("DELETE FROM UsersChannels u where u.username = :username");
		query.setParameter("username", username);
		query.executeUpdate();
	}

}
