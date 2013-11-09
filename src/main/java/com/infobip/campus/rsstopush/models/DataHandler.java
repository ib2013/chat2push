package com.infobip.campus.rsstopush.models;

import java.util.ArrayList;

public class DataHandler {

	public DataHandler() {
	}
	
	public ArrayList<RssFeedModel> fetchAllRssFeedModels() {
		return new ArrayList<RssFeedModel>(RssFeedModel.findAllRssFeedModels());
	}

	public ArrayList<RssSourceModel> fetchAllRssSourceModels() {
		return new ArrayList<RssSourceModel>(RssSourceModel.findAllRssSourceModels());
	}

	public boolean insertIntoRssFeed(RssFeedModel model) {
		model.persist();
		return true;
	}

	public boolean deleteFromRssPopis(RssFeedModel model) {
		model.clear();
		return true;
	}

	public boolean insertIntoRssSource(RssSourceModel model) {
		model.persist();
		return true;
	}

	public boolean deleteFromRssSource(RssSourceModel model) {
		model.clear();
		return true;
	}
}