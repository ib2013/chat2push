package com.infobip.campus.rsstopush.services;

import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.gson.Gson;
import com.infobip.campus.rsstopush.adapters.*;
import com.infobip.campus.rsstopush.adapters.models.MessageModel;
import com.infobip.campus.rsstopush.configuration.Configuration;
import com.infobip.campus.rsstopush.web.CronJobController;

public class PushNotification {

	final String messageID = "ibnmessage";
	final String applicationID = Configuration.APPLICATION_ID;
	String notificationMessage = "-";
	Data androidData;
	String url = null;
	String sentType = "channels";
	String mimeType = "text/html";
	ArrayList<String> channelNames = new ArrayList<String>();
	ArrayList<String> OSTypes = new ArrayList<String>();

	public PushNotification() {

	}

	public PushNotification(MessageModel x, String channelName) {
		this.notificationMessage = x.getTitle();

		this.url = x.getLink();
		OSTypes.add("Android");

		SourceAdapterContainer adapterContainer = new SourceAdapterContainer();
		ArrayList<SourceAdapter> adapters = adapterContainer.getAdapters();

		for (SourceAdapter adapter : adapters) {
			if (adapter.isValid(x.getLink())) {
				androidData = new Data(adapter.getAdapterdescription());
				break;
			}
		}

		channelNames.add(channelName);
	}

	public void notifyChannel(String channelName) {
		Gson gson = new Gson();
		try {
			URL url = new URL("https://pushapi.infobip.com/3/application/"
					+ Configuration.APPLICATION_ID + "/message");
			HTTPRequest request = new HTTPRequest(url, HTTPMethod.POST);

			request.addHeader(new HTTPHeader("Authorization",
					Configuration.AUTHORIZATION_INFO));
			request.addHeader(new HTTPHeader("content-type", "application/json"));
			request.setPayload(gson.toJson(this).getBytes());

			HTTPResponse response = URLFetchServiceFactory.getURLFetchService()
					.fetch(request);

			Logger LOG = LoggerFactory.getLogger(CronJobController.class);
			LOG.info(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void broadcastDeletedChannel(String channelName) {
		this.androidData = new Data("SYSTEM_" + channelName, true);
		sentType = "application";
		OSTypes.add("Android");
		mimeType = "text/plain";

		Gson gson = new Gson();
		try {

			URL url = new URL("https://pushapi.infobip.com/3/application/"
					+ Configuration.APPLICATION_ID + "/message");
			HTTPRequest request = new HTTPRequest(url, HTTPMethod.POST);

			request.addHeader(new HTTPHeader("Authorization",
					Configuration.AUTHORIZATION_INFO));
			request.addHeader(new HTTPHeader("content-type", "application/json"));
			request.setPayload(gson.toJson(this).getBytes());

			HTTPResponse response = URLFetchServiceFactory.getURLFetchService()
					.fetch(request);

			Logger LOG = LoggerFactory.getLogger(CronJobController.class);
			LOG.info(response.toString());

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
