package com.infobip.campus.chattopush.services;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UserModel;

public interface ChannelService {

	public abstract ArrayList<ChannelModel> fetchChannelList();

//	public abstract ArrayList<ChannelModel> parseJson(String jsonResponse);

	public abstract boolean addChannel(ChannelModel channel);

	public abstract boolean deleteChannel(ChannelModel channel);

	public abstract boolean updateChannel(ChannelModel oldModel,
			ChannelModel newModel);
	public abstract JsonArray fetchSubscribedUserByChannelListService(String username);
	
	public abstract boolean addUserToRoom(JsonObject object);
	
	public JsonArray fetchUserByChannel(ChannelModel channelName);

}