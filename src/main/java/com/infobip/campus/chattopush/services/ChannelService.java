package com.infobip.campus.chattopush.services;

import java.util.ArrayList;

import com.infobip.campus.chattopush.models.ChannelModel;

public interface ChannelService {

	public abstract ArrayList<ChannelModel> fetchChannelList();

//	public abstract ArrayList<ChannelModel> parseJson(String jsonResponse);

	public abstract boolean addChannel(ChannelModel channel);

	public abstract boolean deleteChannel(ChannelModel channel);

	public abstract boolean updateChannel(ChannelModel oldModel,
			ChannelModel newModel);

}