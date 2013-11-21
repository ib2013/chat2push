package com.infobip.campus.chattopush.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UsersChannels;

public class ChannelRepository {

	@PersistenceContext
	protected EntityManager em;

	public void deleteChannelDb(String channelName) {
		Query query = em
				.createQuery("DELETE FROM ChannelModel chm WHERE chm.name=:cn ");
		query.setParameter("cn", channelName);
		query.executeUpdate();
	}

	public void deleteRelationsDb(String channelName) {
		Query queryDeleteRelations = em
				.createQuery("DELETE FROM UsersChannels uc WHERE uc.channel=:cn ");
		queryDeleteRelations.setParameter("cn", channelName);
		int n = queryDeleteRelations.executeUpdate();
		System.out.println(n);

	}

	public void removeUserFromRoomDb(UsersChannels relation) {
		Query query = em
				.createQuery("DELETE FROM UsersChannels ch WHERE ch.username=:cu AND ch.channel = :ch ");
		query.setParameter("cu", relation.getUsername());
		query.setParameter("ch", relation.getChannel());
		query.executeUpdate();
	}

	public ChannelModel findChannelDb(String channelName) {
		Query queryFindChannel = em
				.createQuery("SELECT ch FROM ChannelModel ch WHERE ch.name=:ch ");
		queryFindChannel.setParameter("ch", channelName);
		@SuppressWarnings("unchecked")
		List<ChannelModel> cm = queryFindChannel.getResultList();

		ChannelModel result = null;

		if (cm.size() > 0) {
			result = cm.get(0);
		}

		return result;
	}

	public UsersChannels findUserInChannelDb(UsersChannels relations) {
		Query query = em
				.createQuery("SELECT cm FROM UsersChannels cm WHERE cm.username=:cu AND cm.channel=:cn ");
		query.setParameter("cu", relations.getUsername());
		query.setParameter("cn", relations.getChannel());
		@SuppressWarnings("unchecked")
		List<UsersChannels> cm = query.getResultList();

		UsersChannels result = null;

		if (cm.size() > 0) {
			result = cm.get(0);
		}

		return result;
	}

}
