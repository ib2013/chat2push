package com.infobip.campus.chattopush.services.mock;

import java.util.List;


import com.infobip.campus.chattopush.clients.ClientChannelModel;
import com.infobip.campus.chattopush.clients.UserActivityModel;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.ChannelService;

public class ChannelServiceMock implements ChannelService {

	@Override
	public List<ChannelModel> fetchChannelList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addChannel(ChannelModel channel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteChannel(ChannelModel channel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateChannel(ChannelModel oldModel, ChannelModel newModel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ClientChannelModel> fetchSubscribedChannels(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUserToRoom(UsersChannels object) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean removeUserFromRoom(UsersChannels object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserActivityModel> fetchUserByChannel(ChannelModel channelName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isChannelExists(ChannelModel channel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExistsUserInChannel(UsersChannels relations) {
		// TODO Auto-generated method stub
		return false;
	}

}
