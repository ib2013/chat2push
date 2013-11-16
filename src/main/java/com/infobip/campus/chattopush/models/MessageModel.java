package com.infobip.campus.chattopush.models;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class MessageModel {

    /**
     */
    private String username;

    /**
     */
    private String channel;

    /**
     */
    private String message;

    /**
     */
    private long messageDate;
}
