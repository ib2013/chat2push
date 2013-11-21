package com.infobip.campus.chattopush.services;

import java.net.URL;
import java.util.ArrayList;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.infobip.campus.chattopush.configuration.Configuration;
import com.infobip.campus.chattopush.exceptions.CustomException;
import com.infobip.campus.chattopush.exceptions.ErrorCode;
import com.infobip.campus.chattopush.models.MessageModel;

public class PushNotification {

	final String messageID = "ibnmessage";
	final String applicationID = Configuration.APPLICATION_ID;
	String notificationMessage = "-";
	Data androidData;
	String url = null;
	String sentType = "channels";
	String mimeType = "text/plain";
	ArrayList<String> channelNames = new ArrayList<String>();
	ArrayList<String> OSTypes = new ArrayList<String>();

	public PushNotification() {

	}

	public PushNotification(MessageModel msg) {
		JsonObject object = new JsonObject();
		
		object.addProperty("channel", msg.getChannel());
		object.addProperty("sent-by", msg.getUsername());
		object.addProperty("message", msg.getMessage());
		object.addProperty("time", msg.getMessageDate());
		
		this.notificationMessage = object.toString();

		this.url = "";
		OSTypes.add("Android");

		androidData = new Data("MESSAGE");

		channelNames.add(msg.getChannel());
	}

	public void notifyChannel() {
		
		try {
			Gson gson = new Gson();
			URL url = new URL("https://pushapi.infobip.com/3/application/"+ Configuration.APPLICATION_ID +"/message");
			HTTPRequest request = new HTTPRequest(url, HTTPMethod.POST);

			request.addHeader(new HTTPHeader("Authorization",
					Configuration.AUTHORIZATION_INFO));
			request.addHeader(new HTTPHeader("content-type",
					"application/json; charset=utf-8"));
			request.setPayload(gson.toJson(this).getBytes());

			HTTPResponse response = URLFetchServiceFactory.getURLFetchService()
					.fetch(request);
			if (response.getResponseCode() >= 300) {
				throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	
	}

	@Override
	public String toString() {
		return "PushNotification [messageID=" + messageID + ", applicationID="
				+ applicationID + ", notificationMessage="
				+ notificationMessage + ", androidData=" + androidData
				+ ", sentType=" + sentType + ", mimeType=" + mimeType
				+ ", channelNames=" + channelNames + ", OSTypes=" + OSTypes
				+ "]";
	}

	private class Data {
		String title;
		boolean sound = true;
		boolean vibrate = true;
		boolean light = true;

		public Data(String title) {
			this.title = title;
		}

		public Data(String title, boolean isSystem) {
			this.title = title;
			if (isSystem) {
				sound = vibrate = light = false;
			} else {
				sound = vibrate = light = true;
			}
		}
	}

}
