package com.infobip.campus.chattopush.models;

import javax.jdo.annotations.Unique;
import javax.persistence.Entity;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Entity
public class ChannelModel {

	/**
     */
	@Unique
	private String name;

	/**
     */
	private boolean isPublic;

	/**
     */
	private String description;

	public ChannelModel() {

	}

	public ChannelModel(String name, String description) {
		this.name = name;
		this.description = description;
	}
	

}
