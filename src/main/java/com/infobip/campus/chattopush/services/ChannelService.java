package com.infobip.campus.chattopush.services;

import java.util.List;

import com.google.gson.JsonObject;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UserModel;

public interface ChannelService {

	public List<ChannelModel> fetchChannelList();

	public boolean addChannel(ChannelModel channel);

	public boolean deleteChannel(ChannelModel channel);

	public boolean updateChannel(ChannelModel oldModel, ChannelModel newModel);

	public List<ChannelModel> fetchSubscribedChannels(String username);

	public boolean addUserToRoom(JsonObject object);

	public List<UserModel> fetchUserByChannel(ChannelModel channelName);

}