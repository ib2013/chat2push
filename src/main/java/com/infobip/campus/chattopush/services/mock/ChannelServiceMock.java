package com.infobip.campus.chattopush.services.mock;

import java.util.ArrayList;
import java.util.List;

import com.infobip.campus.chattopush.clients.ClientChannelModel;
import com.infobip.campus.chattopush.clients.UserActivityModel;
import com.infobip.campus.chattopush.configuration.UserConfiguration;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.ChannelService;

public class ChannelServiceMock implements ChannelService {

	@Override
	public List<ChannelModel> fetchChannelList() {
		return UserConfiguration.chnls;
	}

	@Override
	public boolean addChannel(ChannelModel channel) {
		try {
			UserConfiguration.chnls.add(channel);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteChannel(ChannelModel channel) {
		for (ChannelModel ch : UserConfiguration.chnls) {
			if (ch.getName().equals(channel.getName())) {
				UserConfiguration.chnls.remove(ch);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateChannel(ChannelModel oldModel, ChannelModel newModel) {
		for (ChannelModel ch : UserConfiguration.chnls) {
			if (ch.getName().equals(oldModel.getName())) {
				UserConfiguration.chnls.remove(ch);
				UserConfiguration.chnls.add(newModel);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<ClientChannelModel> fetchSubscribedChannels(String username) {
		List<ClientChannelModel> clientList = new ArrayList<ClientChannelModel>();

		for (ChannelModel ch : UserConfiguration.chnls) {
			ClientChannelModel clChM = new ClientChannelModel();
			clChM.setDescription(ch.getDescription());
			clChM.setName(ch.getName());
			clChM.setPublic(ch.isIsPublic());

			boolean exists = false;
			for (int i = 0; i < UserConfiguration.us.size(); i++) {
				if (UserConfiguration.us.get(i).equals(username) && UserConfiguration.cs.get(i).equals(ch.getName())) {
					clChM.setSubscribed(true);
					exists = true;
					break;
				}
			}

			if (!exists)
				clChM.setSubscribed(false);
			clientList.add(clChM);
		}
		return clientList;
	}

	@Override
	public boolean addUserToRoom(UsersChannels object) {
		UserConfiguration.us.add(object.getUsername());
		UserConfiguration.cs.add(object.getChannel());

		return true;
	}

	@Override
	public boolean removeUserFromRoom(UsersChannels object) {
		for (int i = 0; i < UserConfiguration.us.size(); i++) {
			if (UserConfiguration.us.get(i).equals(object.getUsername()) && UserConfiguration.cs.get(i).equals(object.getChannel())) {
				UserConfiguration.us.remove(i);
				UserConfiguration.cs.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<UserActivityModel> fetchUserByChannel(ChannelModel channel) {
		// TODO Auto-generated method stub
		List<UserActivityModel> uAcM = new ArrayList<UserActivityModel>();
		for (int i = 0; i < UserConfiguration.us.size(); i++) {
			if (UserConfiguration.cs.get(i).equals(channel.getName())) {
				UserActivityModel x = new UserActivityModel();
				x.setUsername(UserConfiguration.us.get(i));
				x.setMessageCount(0);
				uAcM.add(x);
			}
		}
		return uAcM;
	}

}
