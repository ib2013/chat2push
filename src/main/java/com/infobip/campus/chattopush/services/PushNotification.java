package com.infobip.campus.chattopush.services;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.infobip.campus.chattopush.configuration.Configuration;
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
		
		object.addProperty("name", msg.getChannel());
		object.addProperty("sent-by", msg.getUser());
		object.addProperty("message", msg.getMessage());
		object.addProperty("time", msg.getLastMessageDate().toString());
		
		this.notificationMessage = object.toString();

		this.url = "";
		OSTypes.add("Android");

		androidData = new Data("MESSAGE");

		channelNames.add(msg.getChannel());
	}

	public void notifyChannel() {
		Gson gson = new Gson();
		try {
			StringEntity parms = new StringEntity(gson.toJson(this));
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(
					"https://pushapi.infobip.com/3/application/"+ Configuration.APPLICATION_ID +"/message");
			request.addHeader("Authorization", Configuration.AUTHORIZATION_INFO);
			request.addHeader("content-type", "application/json");
			request.setEntity(parms);
			HttpResponse response = client.execute(request);

			System.out.println(this.toString());
		} catch (Exception e) {
			e.printStackTrace();
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
