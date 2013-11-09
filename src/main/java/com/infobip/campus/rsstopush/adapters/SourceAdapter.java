package com.infobip.campus.rsstopush.adapters;

import com.infobip.campus.rsstopush.adapters.models.*;
import java.util.ArrayList;

public interface SourceAdapter {

	public abstract ArrayList<MessageModel> getMessages();

	public abstract boolean isValid(int id);

	public abstract void setUrl(String url);
	
	public abstract String getAdapterdescription();
}

