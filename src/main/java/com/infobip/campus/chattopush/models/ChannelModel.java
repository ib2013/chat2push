package com.infobip.campus.chattopush.models;

import javax.jdo.annotations.Unique;
import javax.persistence.Entity;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
<<<<<<< HEAD
import javax.validation.Constraint;
=======
>>>>>>> 90f73c6828e21461fbf338e15123ba103542e701

import org.springframework.format.annotation.DateTimeFormat;
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
