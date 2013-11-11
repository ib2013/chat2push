package com.infobip.campus.chattopush.models;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class UserChannel {

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<UserModel> users = new ArrayList<UserModel>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<ChannelModel> channels = new ArrayList<ChannelModel>();

    /**
     */
    private String status;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date lastMessageDate;
}
