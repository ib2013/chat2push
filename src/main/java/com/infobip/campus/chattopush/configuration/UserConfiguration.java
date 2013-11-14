package com.infobip.campus.chattopush.configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.models.UserModel;

public class UserConfiguration {
	public static ArrayList<ChannelModel> chnls = new ArrayList<ChannelModel>();
	public static ArrayList<UserModel> usrs = new ArrayList<UserModel>();
	public static ArrayList<MessageModel> msgs = new ArrayList<MessageModel>();

	public static ArrayList<String> cs = new ArrayList<String>();
	public static ArrayList<String> us = new ArrayList<String>();

	public static Date datum = new Date();
	public static DateFormat datumFormat = new SimpleDateFormat("MMM D, YYYY hh:mm:ss aaa");

	static {
		chnls = new ArrayList<ChannelModel>();
		usrs = new ArrayList<UserModel>();

		chnls.add(new ChannelModel("kanal1", "room1"));
		chnls.add(new ChannelModel("kanal2", "room2"));
		chnls.add(new ChannelModel("kanal3", "room3"));
		chnls.add(new ChannelModel("TEST", "josip"));

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

		cs.add("TEST");
		us.add("korisnik1");

		/* Kanal 1 */
		generirajPoruke("kanal1");
		/* Kanal 2 */
		generirajPoruke("kanal2");
		/* Kanal 3 */
		generirajPoruke("kanal3");
		/* TEST */
		generirajPoruke("TEST");

	}

	public static void generirajPoruke(String kanal) {
		int brojPoruka = randomGenerator(500);
		for (int i = 0; i < brojPoruka; i++) {
			MessageModel poruka = new MessageModel();

			poruka.setChannel(kanal);
			poruka.setUser(us.get(randomGenerator(us.size())));
			poruka.setMessage(Integer.toString(randomGenerator(10000)));
			poruka.setLastMessageDate(new Date());

			msgs.add(poruka);
		}
	}

	public static int randomGenerator(int broj) {
		Random generator = new Random();
		return generator.nextInt(broj);
	}

}
