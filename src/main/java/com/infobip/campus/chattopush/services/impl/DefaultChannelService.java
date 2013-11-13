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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.chattopush.channels.ChannelService#fetchChannelList()
	 */
	@Override
	public ArrayList<ChannelModel> fetchChannelList() {
		ArrayList<ChannelModel> channelList = new ArrayList<ChannelModel>(
				ChannelModel.findAllChannelModels());
		return channelList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.chattopush.channels.ChannelService#parseJson(java.lang
	 * .String)
	 */
	/*
	 * @Override public ArrayList<ChannelModel> parseJson(String jsonResponse) {
	 * 
	 * }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.rsstopush.channels.ChannelService#addChannel(com.infobip
	 * .campus.rsstopush.channels.ChannelModel)
	 */
	@Override
	public boolean addChannel(ChannelModel channel) {

	
		channel.persist();

		Gson gson = new Gson();
		try {
			URL url = new URL("https://pushapi.infobip.com/1/application/"
					+ Configuration.APPLICATION_ID + "/channel");
			HTTPRequest request = new HTTPRequest(url, HTTPMethod.POST);

			request.addHeader(new HTTPHeader("Authorization",
					Configuration.AUTHORIZATION_INFO));
			request.addHeader(new HTTPHeader("content-type",
					"application/json; charset=utf-8"));
			request.setPayload(gson.toJson(channel).getBytes());

			HTTPResponse response = URLFetchServiceFactory.getURLFetchService()
					.fetch(request);
			String responseText = new String(response.getContent());

			return true; // response.getResponseCode() == 200;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.rsstopush.channels.ChannelService#deleteChannel(com
	 * .infobip.campus.rsstopush.channels.ChannelModel)
	 */
	@Override
	public boolean deleteChannel(ChannelModel channel) {
		List<ChannelModel> channels = ChannelModel.findAllChannelModels();

		for (ChannelModel channelElement : channels) {
			if (channelElement.getName().equals(channel.getName())) {
				channelElement.remove();
				break;
			}
		}
		Gson gson = new Gson();
		try {
			String channelName = channel.getName().replaceAll(" ", "%20");
			URL url = new URL("https://pushapi.infobip.com/1/application/"
					+ Configuration.APPLICATION_ID + "/channel/" + channelName);
			HTTPRequest request = new HTTPRequest(url, HTTPMethod.DELETE);

			request.addHeader(new HTTPHeader("Authorization",
					Configuration.AUTHORIZATION_INFO));
			request.addHeader(new HTTPHeader("applicationID",
					Configuration.APPLICATION_ID));
			request.addHeader(new HTTPHeader("channelName", channel.getName()));

			HTTPResponse response = URLFetchServiceFactory.getURLFetchService()
					.fetch(request);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.rsstopush.channels.ChannelService#updateChannel(com
	 * .infobip.campus.rsstopush.channels.ChannelModel,
	 * com.infobip.campus.rsstopush.channels.ChannelModel)
	 */
	@Override
	public boolean updateChannel(ChannelModel oldModel, ChannelModel newModel) {

		Gson gson = new Gson();
		try {
			String formatSpace = oldModel.getName().replaceAll(" ", "%20");
			URL url = new URL("https://pushapi.infobip.com/1/application/"
					+ Configuration.APPLICATION_ID + "/channel/" + formatSpace);
			HTTPRequest request = new HTTPRequest(url, HTTPMethod.PUT);

			request.addHeader(new HTTPHeader("Authorization",
					Configuration.AUTHORIZATION_INFO));
			request.addHeader(new HTTPHeader("content-type", "application/json"));
			request.addHeader(new HTTPHeader("channelName", oldModel.getName()));
			request.setPayload(gson.toJson(newModel).getBytes());

			HTTPResponse response = URLFetchServiceFactory.getURLFetchService()
					.fetch(request);
			String responseText = new String(response.getContent());

			if (responseText.contains("HTTP/1.1 201 Created")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public JsonArray fetchSubscribedUserByChannelListService(String username) {
		List<ChannelModel> channels = ChannelModel.findAllChannelModels();
		JsonArray channelsArray = new JsonArray();
		for (ChannelModel channelElement : channels) {
			JsonObject obj = new JsonObject();
			obj.addProperty("name", channelElement.getName());
			obj.addProperty("description", channelElement.getDescription());
			boolean findUser = false;
			for (UsersChannels relations : UsersChannels
					.findAllUsersChannelses()) {
				if (relations.getUsername().equals(username)
						&& relations.getChannel().equals(
								channelElement.getName())) {
					obj.addProperty("isSubscribed", true);
					findUser = true;
					break;
				}
			}
			if (!findUser) {
				obj.addProperty("isSubscribed", false);
			}
			channelsArray.add(obj);
		}
		return channelsArray;
	}

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

	public JsonArray fetchUserByChannel(ChannelModel channel) {
		JsonArray arr = new JsonArray();
		for (UsersChannels relations : UsersChannels.findAllUsersChannelses()) {
			if (channel.getName().equals(relations.getChannel())) {
				JsonObject obj = new JsonObject();
				obj.addProperty("username", relations.getUsername());
				obj.addProperty(
						"messageCount",
						countMessagesByUserAndChannel(relations.getChannel(),
								relations.getUsername()));
				arr.add(obj);
			}
		}
		return arr;

	}

	private int countMessagesByUserAndChannel(String channelName,
			String username) {
		int counter = 0;
		ArrayList<MessageModel> msgModel = null;
		try{
			msgModel = (ArrayList<MessageModel>)MessageModel.findAllMessageModels();
		}
		catch(Exception e){
			e.printStackTrace();
			msgModel = new ArrayList<MessageModel>();
		}
		for (MessageModel msg : msgModel) {
			if (msg.getUser().getUsername().equals(username)
					&& msg.getChannel().getName().equals(channelName)) {
				counter++;
			}
		}

		return counter;
	}
}
