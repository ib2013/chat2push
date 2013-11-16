package com.infobip.campus.chattopush.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.infobip.campus.chattopush.clients.ClientChannelModel;
import com.infobip.campus.chattopush.clients.UserActivityModel;
import com.infobip.campus.chattopush.configuration.InfobipCommunication;
import com.infobip.campus.chattopush.database.ChannelRepository;
import com.infobip.campus.chattopush.database.UserChannelsRepository;
import com.infobip.campus.chattopush.exceptions.CustomException;
import com.infobip.campus.chattopush.exceptions.ErrorCode;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.ChannelService;

@Service
public class DefaultChannelService implements ChannelService {

	ChannelRepository channelRepository;
	InfobipCommunication infobipCommunication;
	UserChannelsRepository userChannelsRepository;

	public void setUserChannelsRepository(
			UserChannelsRepository userChannelsRepository) {
		this.userChannelsRepository = userChannelsRepository;
	}

	public void setInfobipCommunication(
			InfobipCommunication infobipCommunication) {
		this.infobipCommunication = infobipCommunication;
	}

	public void setChannelRepository(ChannelRepository channelRepository) {
		this.channelRepository = channelRepository;
	}

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
			infobipCommunication.addChannelInfobip(channel);
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
			infobipCommunication.deleteChannelInfobip(channel);
			channelRepository.deleteChannelDb(channel.getName());
			channelRepository.deleteRelationsDb(channel.getName());
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<ClientChannelModel> fetchSubscribedChannels(String username) {

		List<ClientChannelModel> result = new ArrayList<ClientChannelModel>();

		List<ChannelModel> channels = ChannelModel.findAllChannelModels();

		Map<String, ChannelModel> channelsMap = createChannelMap(channels);

		Collection<UsersChannels> ucs = userChannelsRepository
				.getSubscribedChannels(username);

		for (UsersChannels uc : ucs) {
			ChannelModel cm = channelsMap.get(uc.getChannel());
			if (cm == null) {
				continue;
			}
			ClientChannelModel ccm = new ClientChannelModel();
			ccm.setSubscribed(true);
			ccm.setDescription(cm.getDescription());
			ccm.setName(cm.getName());
			ccm.setPublic(cm.isIsPublic());
			result.add(ccm);
		}

		return result;
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
			channelRepository.removeUserFromRoomDb(object);
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

	@Override
	public boolean channelExists(ChannelModel channel) {

		if (channelRepository.findChannelDb(channel.getName()) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean existsUserInChannel(UsersChannels relations) {
		if (channelRepository.findUserInChannelDb(relations) != null) {
			return true;
		} else {
			return false;
		}

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

	@Override
	public Map<String, Integer> channelStatistics() {
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

	private Map<String, ChannelModel> createChannelMap(
			List<ChannelModel> channels) {
		Map<String, ChannelModel> mapa = new HashMap<String, ChannelModel>();
		for (ChannelModel cm : channels) {
			mapa.put(cm.getName(), cm);
		}
		return mapa;
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
			if (msg.getUsername().equals(username)
					&& msg.getChannel().equals(channelName)) {
				counter++;
			}
		}

		return counter;
	}

}
