package com.infobip.campus.chattopush.services.mock;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.services.ChannelService;

public class ChannelServiceMock implements ChannelService {

	@Override
	public ArrayList<ChannelModel> fetchChannelList() {
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
	public JsonArray fetchSubscribedUserByChannelListService(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUserToRoom(JsonObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JsonArray fetchUserByChannel(ChannelModel channelName) {
		// TODO Auto-generated method stub
		return null;
	}

}
