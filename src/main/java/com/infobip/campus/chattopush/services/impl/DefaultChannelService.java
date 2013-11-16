package com.infobip.campus.chattopush.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.infobip.campus.chattopush.clients.ClientChannelModel;
import com.infobip.campus.chattopush.clients.UserActivityModel;
import com.infobip.campus.chattopush.configuration.InfobipCommunication;
import com.infobip.campus.chattopush.exceptions.CustomException;
import com.infobip.campus.chattopush.exceptions.ErrorCode;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.ChannelService;

@Service
public class DefaultChannelService implements ChannelService {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public ArrayList<ChannelModel> fetchChannelList() {
		ArrayList<ChannelModel> channelList = new ArrayList<ChannelModel>(
				ChannelModel.findAllChannelModels());
		return channelList;
	}

	@Override
	public void addChannel(ChannelModel channel) throws CustomException {
		if (channelExists(channel)) {
			throw new CustomException(ErrorCode.CHANNEL_ALLREADY_EXISTS);
		} else {
			InfobipCommunication add = new InfobipCommunication();
			add.addChannelInfoBip(channel);
			try {
				channel.persist();
			} catch (Exception e) {
				throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
			}
		}

	}

	@Override
	public void deleteChannel(ChannelModel channel) {
		try {
			InfobipCommunication delete = new InfobipCommunication();
			delete.deleteChannelInfoBip(channel);

			/*
			 * Query query =
			 * em.createQuery("DELETE FROM ChannelModel ch WHERE ch.name=:cn ");
			 * query.setParameter("cn", channel.getName());
			 * query.executeUpdate() /*List<ChannelModel> channels =
			 * ChannelModel.findAllChannelModels(); String removChannel = "";
			 * for (ChannelModel channelElement : channels) { if
			 * (channelElement.getName().equals(channel.getName())) {
			 * removChannel = channelElement.getName(); channelElement.remove();
			 * break; } }
			 */

			Query query = em
					.createQuery("DELETE FROM UsersChannels ch WHERE ch.name=:cn ");
			query.setParameter("cn", channel.getName());
			query.executeUpdate();
			/*
			 * List<UsersChannels> relations = new ArrayList<UsersChannels>(
			 * UsersChannels.findAllUsersChannelses()); for (UsersChannels
			 * releationElement : relations) { if
			 * (releationElement.getChannel().equals(removChannel)) {
			 * releationElement.remove(); break; } }
			 */
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<ClientChannelModel> fetchSubscribedChannels(String username) {

		List<ClientChannelModel> returnParameters = new ArrayList<ClientChannelModel>();
		List<ChannelModel> channels = ChannelModel.findAllChannelModels();

		for (ChannelModel channelElement : channels) {
			ClientChannelModel clientObject = new ClientChannelModel();
			clientObject.setName(channelElement.getName());
			clientObject.setDescription(channelElement.getDescription());
			clientObject.setPublic(channelElement.isIsPublic());
			boolean findUser = false;
			for (UsersChannels relations : UsersChannels
					.findAllUsersChannelses()) {
				if (relations.getUsername().equals(username)
						&& relations.getChannel().equals(
								channelElement.getName())) {
					clientObject.setSubscribed(true);
					findUser = true;
					break;
				}
			}
			if (!findUser) {
				clientObject.setSubscribed(false);
			}

			if (clientObject.isSubscribed() || clientObject.isPublic()) {
				returnParameters.add(clientObject);
				System.out.println(clientObject.getName()
						+ clientObject.isSubscribed() + clientObject.isPublic()
						+ " --- " + findUser);
			}
		}
		return returnParameters;
	}

	@Override
	public void addUserToRoom(UsersChannels object) {
		ChannelModel channel = new ChannelModel();
		channel.setName(object.getChannel());
		channel.setDescription("");
		if (channelExists(channel) && !existsUserInChannel(object)) {
			try {
				object.setLastMessage(new Date(0));
				object.persist();
			} catch (Exception e) {
				throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
			}
		} else {
			throw new CustomException(ErrorCode.CHANNEL_USER_EXISTS);
		}
	}

	public void removeUserFromRoom(UsersChannels object) {
		try {
			Query query = em
					.createQuery("DELETE FROM UsersChannels ch WHERE ch.username=:cu AND ch.channel = :ch ");
			query.setParameter("cu", object.getUsername());
			query.setParameter("ch", object.getChannel());

		} catch (Exception e) {
			throw new CustomException(ErrorCode.CHANNEL_USER_EXISTS);
		}
	}

	@Override
	public List<UserActivityModel> fetchUserByChannel(ChannelModel channel) {
		List<UserActivityModel> activityUsers = new ArrayList<UserActivityModel>();

		for (UsersChannels relations : UsersChannels.findAllUsersChannelses()) {
			if (channel.getName().equals(relations.getChannel())) {
				UserActivityModel userObject = new UserActivityModel();
				userObject.setUsername(relations.getUsername());
				userObject.setMessageCount(countMessagesByUserAndChannel(
						relations.getChannel(), relations.getUsername()));
				activityUsers.add(userObject);
			}
		}
		return activityUsers;
	}

	private int countMessagesByUserAndChannel(String channelName,
			String username) {
		int counter = 0;
		ArrayList<MessageModel> msgModel = null;
		try {
			msgModel = new ArrayList<MessageModel>(
					MessageModel.findAllMessageModels());
		} catch (Exception e) {
			e.printStackTrace();
			msgModel = new ArrayList<MessageModel>();
		}
		for (MessageModel msg : msgModel) {
			if (msg.getUser().equals(username)
					&& msg.getChannel().equals(channelName)) {
				counter++;
			}
		}

		return counter;
	}

	public boolean channelExists(ChannelModel channel) {
		List<ChannelModel> channels = new ArrayList<ChannelModel>(
				ChannelModel.findAllChannelModels());

		for (ChannelModel channelIterator : channels) {
			if (channelIterator.getName().equals(channel.getName())) {
				return true;
			}
		}
		return false;
	}

	public boolean existsUserInChannel(UsersChannels relations) {
		List<UsersChannels> allRelations = new ArrayList<UsersChannels>(
				UsersChannels.findAllUsersChannelses());
		for (UsersChannels userChannel : allRelations) {
			if (userChannel.getChannel().equals(relations.getChannel())
					&& userChannel.getUsername()
							.equals(relations.getUsername())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<UserModel> fetchOpositeUserByChannel(ChannelModel channel) {
		List<UserActivityModel> activityUsers = new ArrayList<UserActivityModel>(
				fetchUserByChannel(channel));
		List<UserModel> users = new ArrayList<UserModel>(
				UserModel.findAllUserModels());
		List<UserModel> usersRoom = new ArrayList<UserModel>();
		boolean find;
		for (UserModel user : users) {
			find = false;
			for (UserActivityModel relations : activityUsers) {
				if (user.getUsername().equals(relations.getUsername())) {
					find = true;
				}
			}
			if (!find) {
				usersRoom.add(user);
			}
		}
		return usersRoom;
	}

	public Map<String, Integer> channelStatistics() throws CustomException {
		// TODO Auto-generated method stub
		try {
			List<ChannelModel> channels = ChannelModel.findAllChannelModels();
			List<MessageModel> messages = MessageModel.findAllMessageModels();

			Map<String, Integer> statistic = new HashMap<String, Integer>();

			for (ChannelModel chnlModel : channels) {
				int numMessage = 0;
				for (MessageModel msgModel : messages) {
					if (msgModel.getChannel()
							.contentEquals(chnlModel.getName())) {
						numMessage++;
					}
				}
				statistic.put(chnlModel.getName(), numMessage);
			}
			return statistic;
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}

	}

}
