package com.infobip.campus.chattopush.services;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.gson.Gson;
import com.infobip.campus.chattopush.configuration.Configuration;

import com.infobip.campus.chattopush.models.ChannelModel;


import java.net.URL;
import java.util.ArrayList;

import org.springframework.stereotype.Service;


@SuppressWarnings("deprecation")
@Service
public class DefaultChannelService implements ChannelService {


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.rsstopush.channels.ChannelService#fetchChannelList()
	 */
	@Override
	public ArrayList<ChannelModel> fetchChannelList() {
		ArrayList<ChannelModel> channelList = new ArrayList<ChannelModel>(ChannelModel.findAllChannelModels());
		return channelList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.rsstopush.channels.ChannelService#parseJson(java.lang
	 * .String)
	 */
	/*@Override
	public ArrayList<ChannelModel> parseJson(String jsonResponse) {

	}*/

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
}