package com.infobip.campus.rsstopush.models;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class RssFeedModel {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rssUrl == null) ? 0 : rssUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RssFeedModel other = (RssFeedModel) obj;
		if (rssUrl == null) {
			if (other.rssUrl != null)
				return false;
		} else if (!rssUrl.equals(other.rssUrl))
			return false;
		return true;
	}

	private String rssUrl;
	private String description;

}
