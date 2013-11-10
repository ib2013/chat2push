package com.infobip.campus.rsstopush.channels;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.gson.Gson;
import com.infobip.campus.rsstopush.configuration.Configuration;
import com.infobip.campus.rsstopush.web.CronJobController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SuppressWarnings("deprecation")
@Service
public class DefaultChannelService implements ChannelService {
	private static final Logger LOG = LoggerFactory
			.getLogger(CronJobController.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.rsstopush.channels.ChannelService#fetchChannelList()
	 */
	@Override
	public ArrayList<ChannelModel> fetchChannelList() {
		ArrayList<ChannelModel> channelList;
		try {
			Gson gson = new Gson();

			URL url = new URL("https://pushapi.infobip.com/1/application/"
					+ Configuration.APPLICATION_ID + "/channels");
			HTTPRequest request = new HTTPRequest(url, HTTPMethod.GET);

			request.addHeader(new HTTPHeader("Authorization",
					Configuration.AUTHORIZATION_INFO));

			HTTPResponse response = URLFetchServiceFactory.getURLFetchService()
					.fetch(request);

			String responseText = new String(response.getContent());

			channelList = parseJson(responseText);
			return channelList;

		} catch (Exception e) {
			LOG.info(e.getMessage());
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.infobip.campus.rsstopush.channels.ChannelService#parseJson(java.lang
	 * .String)
	 */
	@Override
	public ArrayList<ChannelModel> parseJson(String jsonResponse) {
		ArrayList<ChannelModel> channelList = new ArrayList<ChannelModel>();

		JsonParser jsonParser = new JsonParser();
		JsonElement jsonTree = jsonParser.parse(jsonResponse);
		JsonArray jsonArray = jsonTree.getAsJsonArray();

		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonElement = jsonArray.get(i).getAsJsonObject();
			String channelName;
			String channelDescription;

			try {
				channelName = jsonElement.getAsJsonPrimitive("name")
						.getAsString();
			} catch (Exception e) {
				channelName = "";
			}
			try {
				channelDescription = jsonElement.getAsJsonPrimitive(
						"description").getAsString();
			} catch (Exception e) {
				channelDescription = "";
			}

			channelList.add(new ChannelModel(channelName, channelDescription));
		}

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
		Gson gson = new Gson();
		try {
			URL url = new URL("https://pushapi.infobip.com/1/application/"
					+ Configuration.APPLICATION_ID + "/channel");
			HTTPRequest request = new HTTPRequest(url, HTTPMethod.POST);

			request.addHeader(new HTTPHeader("Authorization",
					Configuration.AUTHORIZATION_INFO));
			request.addHeader(new HTTPHeader("content-type", "application/json"));
			request.setPayload(gson.toJson(channel).getBytes());

			HTTPResponse response = URLFetchServiceFactory.getURLFetchService()
					.fetch(request);
			String responseText = new String(response.getContent());

			return true; //response.getResponseCode() == 200;
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
