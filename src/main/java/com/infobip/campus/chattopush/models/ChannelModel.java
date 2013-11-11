package com.infobip.campus.chattopush.models;
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
}
