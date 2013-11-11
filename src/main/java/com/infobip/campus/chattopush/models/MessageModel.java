package com.infobip.campus.chattopush.models;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class MessageModel {

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private UserModel user = new UserModel();
    
    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private ChannelModel channel = new ChannelModel();

    /**
     */
    private String message;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Date lastMessageDate;
}
