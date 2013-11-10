package com.infobip.campus.rsstopush.adapters;

import com.infobip.campus.rsstopush.adapters.models.*;
import com.infobip.campus.rsstopush.models.RssSourceModel;

import java.util.ArrayList;

public interface SourceAdapter {

	public abstract ArrayList<MessageModel> getMessages();

	public abstract boolean isValid(String rssUrl);
	
	public abstract boolean isFeasible(String url);

	public abstract void setUrl(String url);
	
	public abstract String getAdapterdescription();
}

