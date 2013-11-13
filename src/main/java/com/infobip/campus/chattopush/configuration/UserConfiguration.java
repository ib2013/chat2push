package com.infobip.campus.chattopush.configuration;

import java.util.ArrayList;

import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UserModel;

public class UserConfiguration {
	public static ArrayList<ChannelModel> chnls = new ArrayList<ChannelModel>();
	public static ArrayList<UserModel> usrs = new ArrayList<UserModel>();

	public static ArrayList<String> cs = new ArrayList<String>();
	public static ArrayList<String> us = new ArrayList<String>();

	static {
		chnls = new ArrayList<ChannelModel>();
		usrs = new ArrayList<UserModel>();

		chnls.add(new ChannelModel("kanal1", "room1"));
		chnls.add(new ChannelModel("kanal2", "room2"));
		chnls.add(new ChannelModel("kanal3", "room3"));

		usrs.add(new UserModel("korisnik1", "pass1"));
		usrs.add(new UserModel("korisnik2", "pass2"));
		usrs.add(new UserModel("korisnik3", "pass3"));
		usrs.add(new UserModel("korisnik4", "pass4"));
		usrs.add(new UserModel("korisnik5", "pass5"));
		
		cs.add("kanal1");
		us.add("korisnik1");
		
		cs.add("kanal1");
		us.add("korisnik2");
		
		cs.add("kanal2");
		us.add("korisnik4");
	}
}
