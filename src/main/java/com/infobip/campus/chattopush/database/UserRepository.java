package com.infobip.campus.chattopush.database;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.infobip.campus.chattopush.models.UserModel;

public class UserRepository {

	@PersistenceContext
	protected EntityManager em;

	public UserModel loginUser(UserModel model) {
		Query query = em.createQuery("SELECT u from UserModel u where u.username = :username");
		query.setParameter("username", model.getUsername());
		return (UserModel) query.getSingleResult();
	}

	public void deleteUser(UserModel model) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("DELETE FROM UserModel u where u.username = :username");
		query.setParameter("username", model.getUsername());
		query.executeUpdate();
	}

	public void removeFromAllChannels(UserModel model) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("DELETE FROM ChannelModel u where u.name = :username");
		query.setParameter("username", model.getUsername());
		query.executeUpdate();
	}

	// public int fetchUserStatistics(UserModel usrModel, ChannelModel chModel) {
	// // TODO Auto-generated method stub
	// Query query = em.createQuery("SELECT count(username) from MessageModel where username= :username AND channel = :channel");
	// query.setParameter("username", usrModel.getUsername());
	// query.setParameter("channel", chModel.getName());
	// return query.getMaxResults();
	// }

}
