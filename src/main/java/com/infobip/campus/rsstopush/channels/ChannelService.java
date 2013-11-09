package com.infobip.campus.rsstopush.channels;

import java.util.ArrayList;

public interface ChannelService {

	public abstract ArrayList<ChannelModel> fetchChannelList();

	public abstract ArrayList<ChannelModel> parseJson(String jsonResponse);

	public abstract boolean addChannel(ChannelModel channel);

	public abstract boolean deleteChannel(ChannelModel channel);

	public abstract boolean updateChannel(ChannelModel oldModel,
			ChannelModel newModel);

}