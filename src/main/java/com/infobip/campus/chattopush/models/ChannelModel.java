package com.infobip.campus.chattopush.models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
public class ChannelModel {

    /**
     */
    private String name;

    /**
     */
    private Boolean isPublic;

    /**
     */
    private String description;
    
    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Date lastMessageDate;
    
    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<UserModel> users = new ArrayList<UserModel>();
}
