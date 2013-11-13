package com.infobip.campus.chattopush.services;

import java.util.List;

import com.google.gson.JsonObject;
import com.infobip.campus.chattopush.clients.ClientChannelModel;
import com.infobip.campus.chattopush.clients.UserActivityModel;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UsersChannels;


public interface ChannelService {

	public List<ChannelModel> fetchChannelList();

	public boolean addChannel(ChannelModel channel);

	public boolean deleteChannel(ChannelModel channel);

	public boolean updateChannel(ChannelModel oldModel, ChannelModel newModel);

	public List<ClientChannelModel> fetchSubscribedChannels(String username);

	public boolean addUserToRoom(UsersChannels object);
	
	public boolean removeUserFromRoom(UsersChannels object);

	public List<UserActivityModel> fetchUserByChannel(ChannelModel channelName);

}