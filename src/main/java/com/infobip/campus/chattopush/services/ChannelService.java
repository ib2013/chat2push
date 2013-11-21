package com.infobip.campus.chattopush.services;

import java.util.List;
import java.util.Map;

import com.infobip.campus.chattopush.clients.ClientChannelModel;
import com.infobip.campus.chattopush.clients.UserActivityModel;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;

public interface ChannelService {

	public List<ChannelModel> fetchChannelList();

	public void addChannel(ChannelModel channel);

	public void deleteChannel(ChannelModel channel);

	
	public boolean channelExists(ChannelModel channel);
	
	public List<ClientChannelModel> fetchSubscribedChannels(String username);

	public void addUserToRoom(UsersChannels object);
	
	public boolean existsUserInChannel(UsersChannels relations);
	
	public void removeUserFromRoom(UsersChannels object);

	public List<UserActivityModel> fetchUserByChannel(ChannelModel channel);
	
	public List<UserModel> fetchOpositeUserByChannel(ChannelModel channel);
	
	public Map<String, Integer> channelStatistics();

}