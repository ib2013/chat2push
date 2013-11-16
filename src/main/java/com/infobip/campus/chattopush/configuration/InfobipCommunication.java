package com.infobip.campus.chattopush.configuration;

import java.net.URL;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.gson.Gson;
import com.infobip.campus.chattopush.exceptions.CustomException;
import com.infobip.campus.chattopush.exceptions.ErrorCode;
import com.infobip.campus.chattopush.models.ChannelModel;

public class InfobipCommunication {

	public static final String URI = "https://pushapi.infobip.com/1/application/"
			+ Configuration.APPLICATION_ID + "/channel";

	public InfobipCommunication() {

	}

	public void addChannelInfoBip(ChannelModel channel) throws CustomException {
		try {
			Gson gson = new Gson();
			URL url = new URL(URI);
			HTTPRequest request = new HTTPRequest(url, HTTPMethod.POST);

			request.addHeader(new HTTPHeader("Authorization",
					Configuration.AUTHORIZATION_INFO));
			request.addHeader(new HTTPHeader("content-type",
					"application/json; charset=utf-8"));
			request.setPayload(gson.toJson(channel).getBytes());

			HTTPResponse response = URLFetchServiceFactory.getURLFetchService()
					.fetch(request);
			if (response.getResponseCode() >= 300) {
				throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	public void deleteChannelInfoBip(ChannelModel channel)
			throws CustomException {
		try {

			String channelName = channel.getName().replaceAll(" ", "%20");
			URL url = new URL(URI + channelName);
			HTTPRequest request = new HTTPRequest(url, HTTPMethod.DELETE);

			request.addHeader(new HTTPHeader("Authorization",
					Configuration.AUTHORIZATION_INFO));
			request.addHeader(new HTTPHeader("applicationID",
					Configuration.APPLICATION_ID));
			request.addHeader(new HTTPHeader("channelName", channel.getName()));

			HTTPResponse response = URLFetchServiceFactory.getURLFetchService()
					.fetch(request);
			if (response.getResponseCode() >= 300) {
				throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

}
