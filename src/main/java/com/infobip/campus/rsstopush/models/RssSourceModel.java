package com.infobip.campus.rsstopush.models;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class RssSourceModel {

    /**
     */
    private String sourceName;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<RssFeedModel> rssFeeds = new ArrayList<RssFeedModel>();
}
