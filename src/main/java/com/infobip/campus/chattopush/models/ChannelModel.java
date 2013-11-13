package com.infobip.campus.chattopush.models;
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
    private String name;

    /**
     */
    private boolean isPublic;

    /**
     */
    private String description;

    
    public ChannelModel() {
    	
    }
    
    public ChannelModel(String name, String descriptopn ) {
    	this.name = name;
    	this.description = descriptopn;
    }
    
 
    
}
