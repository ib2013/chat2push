package com.infobip.campus.rsstopush.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infobip.campus.rsstopush.*;
import com.infobip.campus.rsstopush.adapters.SourceAdapter;
import com.infobip.campus.rsstopush.adapters.SourceAdapterContainer;
import com.infobip.campus.rsstopush.adapters.TorrentSourceAdapter;
import com.infobip.campus.rsstopush.adapters.models.MessageModel;
import com.infobip.campus.rsstopush.channels.*;
import com.infobip.campus.rsstopush.models.RssFeedModel;
import com.infobip.campus.rsstopush.configuration.Configuration;
import com.infobip.campus.rsstopush.channels.ChannelModel;

@Service
public class DefaultFeedToPushService implements FeedToPushService {
	HashMap<ChannelModel, Date> lastFeedDates = new HashMap<ChannelModel, Date>();
	HashMap<ChannelModel, Integer> channelNotificationCounter = new HashMap<ChannelModel, Integer>();
	ChannelService channelHandler = new DefaultChannelService();

	public DefaultFeedToPushService() {
	}

	public void readRSSFeeds() {

		PushNotification pushNotification = new PushNotification();
		pushNotification.broadcastDeletedChannel("djeSiJarane");
		ArrayList<RssFeedModel> sourcesList = new ArrayList<RssFeedModel>(
				RssFeedModel.findAllRssFeedModels());
		ArrayList<MessageModel> messagesList = fetchMessageModelListFromSources(sourcesList);
		ArrayList<ChannelModel> channelList = channelHandler.fetchChannelList();

		for (ChannelModel channel : channelList) {
			if (!lastFeedDates.containsKey(channel)) {
				Date date = new Date();
				date.setTime(date.getTime() - 60 * 60 * 1000);
				lastFeedDates.put(channel, date);
			}
			if (!channelNotificationCounter.containsKey(channel)) {
				channelNotificationCounter.put(channel, 0);
			}
		}

		updateUsersWithNotifications(messagesList, channelList);
		System.out.println("======================");
	}

	private ArrayList<MessageModel> fetchMessageModelListFromSources(
			ArrayList<RssFeedModel> sourcesList) {

		ArrayList<MessageModel> messagesList = new ArrayList<MessageModel>();
		SourceAdapterContainer container = new SourceAdapterContainer();
		ArrayList<SourceAdapter> adapters = container.getAdapters();

		for (RssFeedModel rss : sourcesList) {
			for (SourceAdapter adapter : adapters) {
				if (adapter.isValid(rss.getRssUrl())) {
					adapter.setUrl(rss.getRssUrl());
					messagesList.addAll(adapter.getMessages());
				}
			}
		}

		return messagesList;
	}

	public void updateUsersWithNotifications(
			ArrayList<MessageModel> messagesList,
			ArrayList<ChannelModel> channelList) {
		for (MessageModel x : messagesList) {
			for (ChannelModel y : channelList) {
				if (hasMatch(x, y)) {

					PushNotification pushN = new PushNotification(x,
							y.getName());
					pushN.notifyChannel(y.getName());
				}
			}
		}
	}

	public boolean hasMatch(MessageModel message, ChannelModel channel) {

		Date lastTorrentFeedDate = lastFeedDates.get(channel);
		Integer oldCounter = channelNotificationCounter.get(channel);
		if (lastTorrentFeedDate == null) {
			lastFeedDates.put(channel, Configuration.DEFAULT_DATE);
			lastTorrentFeedDate = Configuration.DEFAULT_DATE;
		}
		if (oldCounter == null) {
			channelNotificationCounter.put(channel, 0);
			oldCounter = 0;
		}

		if (message.getDate().compareTo(lastTorrentFeedDate) <= 0)
			return false;

		if (channel.getName().toUpperCase()
				.equals(Configuration.DEFAULT_TPB_NAME.toUpperCase())
				&& (message.getLink().startsWith("http://thepiratebay.sx") || message
						.getLink().startsWith("https://thepiratebay.sx"))) {
			lastFeedDates.put(channel, message.getDate());
			channelNotificationCounter.put(channel, oldCounter + 1);
			return true;
		}

		if (channel.getName().toUpperCase()
				.equals(Configuration.DEFAULT_YT_NAME.toUpperCase())
				&& (message.getLink()
						.startsWith("http://www.youtube.com/watch") || message
						.getLink().startsWith("https://www.youtube.com/watch"))) {
			lastFeedDates.put(channel, message.getDate());
			channelNotificationCounter.put(channel, oldCounter + 1);
			return true;
		}

		if (channel.getName().toUpperCase()
				.equals(Configuration.DEFAULT_CHANNEL_NAME.toUpperCase())) {
			lastFeedDates.put(channel, message.getDate());
			channelNotificationCounter.put(channel, oldCounter + 1);
			return true;
		}

		String[] splitString = channel.getName().split(" ");
		for (int i = 0; i < splitString.length; i++) {
			if (!message.getTitle().toLowerCase()
					.contains(splitString[i].toLowerCase())) {
				return false;
			}
		}

		lastFeedDates.put(channel, message.getDate());
		channelNotificationCounter.put(channel, oldCounter + 1);
		return true;
	}

	public void deleteChannelFromMap(ChannelModel channel) {
		channelNotificationCounter.remove(channel);
		lastFeedDates.remove(channel);
	}

	public HashMap<ChannelModel,Integer> channelMapCounterToJson() {
		return channelNotificationCounter;
	}
}
