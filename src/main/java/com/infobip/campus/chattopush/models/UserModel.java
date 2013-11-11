package com.infobip.campus.chattopush.models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class UserModel {

    /**
     */
    private String googleId;

    /**
     */
    private String username;

    /**
     */
    private String password;
    
    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<ChannelModel> channels = new ArrayList<ChannelModel>();
}
