package com.infobip.campus.chattopush.services.mock;

import java.util.List;

import com.google.gson.JsonObject;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UserModel;
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
	public List<ChannelModel> fetchSubscribedChannels(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUserToRoom(JsonObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserModel> fetchUserByChannel(ChannelModel channelName) {
		// TODO Auto-generated method stub
		return null;
	}

}
