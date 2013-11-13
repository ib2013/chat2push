package com.infobip.campus.chattopush.services.impl;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.infobip.campus.chattopush.configuration.Configuration;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.ChannelService;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

@Service
public class DefaultChannelService implements ChannelService {
	
	@Override
	public List<ChannelModel> fetchSubscribedChannels(String username) {
//		List<ChannelModel> channels = ChannelModel.findAllChannelModels();
//		JsonArray channelsArray = new JsonArray();
//		for (ChannelModel channelElement : channels) {
//			JsonObject obj = new JsonObject();
//			obj.addProperty("name", channelElement.getName());
//			obj.addProperty("description", channelElement.getDescription());
//			boolean findUser = false;
//			for (UsersChannels relations : UsersChannels
//					.findAllUsersChannelses()) {
//				if (relations.getUsername().equals(username)
//						&& relations.getChannel().equals(
//								channelElement.getName())) {
//					obj.addProperty("isSubscribed", true);
//					findUser = true;
//					break;
//				}
//			}
//			if (!findUser) {
//				obj.addProperty("isSubscribed", false);
//			}
//			channelsArray.add(obj);
//		}
//		return channelsArray;
		return null;
	}

	@Override
	public boolean addUserToRoom(JsonObject object) {
		try {
			String channelName = "";
			String userName = "";
			JsonElement elename = object.get("name");
			JsonElement eleusername = object.get("username");
			channelName = elename.getAsString();
			userName = eleusername.getAsString();
			UsersChannels uC = new UsersChannels();
			uC.setChannel(channelName);
			uC.setUsername(userName);
			uC.setLastMessage(new Date());
			uC.persist();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<UserModel> fetchUserByChannel(ChannelModel channel) {
//		JsonArray arr = new JsonArray();
//		for (UsersChannels relations : UsersChannels.findAllUsersChannelses()) {
//			if (channel.getName().equals(relations.getChannel())) {
//				JsonObject obj = new JsonObject();
//				obj.addProperty("username", relations.getUsername());
//				obj.addProperty(
//						"messageCount",
//						countMessagesByUserAndChannel(relations.getChannel(),
//								relations.getUsername()));
//				arr.add(obj);
//			}
//		}
//		return arr;
		return null;
	}

	private int countMessagesByUserAndChannel(String channelName,
			String username) {
		int counter = 0;
		ArrayList<MessageModel> msgModel = null;
		try{
			msgModel = new ArrayList<MessageModel>(MessageModel.findAllMessageModels());
		}
		catch(Exception e){
			e.printStackTrace();
			msgModel = new ArrayList<MessageModel>();
		}
		for (MessageModel msg : msgModel) {
			if (msg.getUser().equals(username)
					&& msg.getChannel().equals(channelName)) {
				counter++;
			}
		}

		return counter;
	}

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
}
