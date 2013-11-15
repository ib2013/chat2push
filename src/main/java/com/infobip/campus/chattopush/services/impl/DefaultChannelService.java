package com.infobip.campus.chattopush.services.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.gson.Gson;
import com.infobip.campus.chattopush.clients.ClientChannelModel;
import com.infobip.campus.chattopush.clients.UserActivityModel;
import com.infobip.campus.chattopush.configuration.Configuration;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.ChannelService;

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
	 * com.infobip.campus.rsstopush.channels.ChannelService#addChannel(com.infobip
	 * .campus.rsstopush.channels.ChannelModel)
	 */
	@Override
	public boolean addChannel(ChannelModel channel) {
		if (isChannelExists(channel)) {
			return false;
		} else {
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

				HTTPResponse response = URLFetchServiceFactory
						.getURLFetchService().fetch(request);
				String responseText = new String(response.getContent());
				try {
					channel.persist();
					return true; // response.getResponseCode() == 200;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				channel.remove();
				return false;
			}
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

			try {
				List<ChannelModel> channels = ChannelModel
						.findAllChannelModels();
				String removChannel = "";
				for (ChannelModel channelElement : channels) {
					if (channelElement.getName().equals(channel.getName())) {
						removChannel = channelElement.getName();
						channelElement.remove();
						break;
					}
				}

				List<UsersChannels> relations = new ArrayList<UsersChannels>(
						UsersChannels.findAllUsersChannelses());
				for (UsersChannels releationElement : relations) {
					if (releationElement.getChannel().equals(removChannel)) {
						releationElement.remove();
						break;
					}
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
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

	@Override
	public List<ClientChannelModel> fetchSubscribedChannels(String username) {

		List<ClientChannelModel> returnParameters = new ArrayList<ClientChannelModel>();
		List<ChannelModel> channels = ChannelModel.findAllChannelModels();

		for (ChannelModel channelElement : channels) {
			ClientChannelModel clientObject = new ClientChannelModel();
			clientObject.setName(channelElement.getName());
			clientObject.setDescription(channelElement.getDescription());
			clientObject.setPublic(channelElement.isIsPublic());
			boolean findUser = false;
			for (UsersChannels relations : UsersChannels
					.findAllUsersChannelses()) {
				if (relations.getUsername().equals(username)
						&& relations.getChannel().equals(
								channelElement.getName())) {
					clientObject.setSubscribed(true);
					findUser = true;
					break;
				}
			}
			if (!findUser) {
				clientObject.setSubscribed(false);
			}

			if (clientObject.isSubscribed() || clientObject.isPublic()) {
				returnParameters.add(clientObject);
				System.out.println(clientObject.getName()
						+ clientObject.isSubscribed() + clientObject.isPublic()
						+ " --- " + findUser);
			}
		}
		return returnParameters;
	}

	@Override
	public boolean addUserToRoom(UsersChannels object) {
		ChannelModel channel = new ChannelModel();
		channel.setName(object.getChannel());
		channel.setDescription("");
		if (isChannelExists(channel) && !isExistsUserInChannel(object)) {
			try {
				object.setLastMessage(new Date(0));
				object.persist();
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean removeUserFromRoom(UsersChannels object) {
		try {
			List<UsersChannels> relations = UsersChannels
					.findAllUsersChannelses();
			for (UsersChannels relationElement : relations) {
				if (relationElement.getChannel().equals(object.getChannel())
						&& relationElement.getUsername().equals(
								object.getUsername())) {
					relationElement.remove();
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<UserActivityModel> fetchUserByChannel(ChannelModel channel) {
		List<UserActivityModel> activityUsers = new ArrayList<UserActivityModel>();

		for (UsersChannels relations : UsersChannels.findAllUsersChannelses()) {
			if (channel.getName().equals(relations.getChannel())) {
				UserActivityModel userObject = new UserActivityModel();
				userObject.setUsername(relations.getUsername());
				userObject.setMessageCount(countMessagesByUserAndChannel(
						relations.getChannel(), relations.getUsername()));
				activityUsers.add(userObject);
			}
		}
		return activityUsers;
	}

	private int countMessagesByUserAndChannel(String channelName,
			String username) {
		int counter = 0;
		ArrayList<MessageModel> msgModel = null;
		try {
			msgModel = new ArrayList<MessageModel>(
					MessageModel.findAllMessageModels());
		} catch (Exception e) {
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

	public boolean isChannelExists(ChannelModel channel) {
		List<ChannelModel> channels = new ArrayList<ChannelModel>(
				ChannelModel.findAllChannelModels());

		for (ChannelModel channelIterator : channels) {
			if (channelIterator.getName().equals(channel.getName())) {
				return true;
			}
		}
		return false;
	}

	public boolean isExistsUserInChannel(UsersChannels relations) {
		List<UsersChannels> allRelations = new ArrayList<UsersChannels>(
				UsersChannels.findAllUsersChannelses());
		for (UsersChannels userChannel : allRelations) {
			if (userChannel.getChannel().equals(relations.getChannel())
					&& userChannel.getUsername()
							.equals(relations.getUsername())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<UserModel> fetchOpositeUserByChannel(ChannelModel channel) {
		List<UserActivityModel> activityUsers = new ArrayList<UserActivityModel>(
				fetchUserByChannel(channel));
		List<UserModel> users = new ArrayList<UserModel>(
				UserModel.findAllUserModels());
		List<UserModel> usersRoom = new ArrayList<UserModel>();
		boolean find;
		for (UserModel user : users) {
			find = false;
			for (UserActivityModel relations : activityUsers) {
				if (user.getUsername().equals(relations.getUsername())) {
					find = true;
				}
			}
			if (!find) {
				usersRoom.add(user);
			}
		}
		return usersRoom;
	}
}
