package com.infobip.campus.chattopush.configuration;

import java.util.ArrayList;

import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UserModel;

public class UserConfiguration {
	static public ArrayList<UserModel> listaKorisnika = new ArrayList<>();
	static public ArrayList<ChannelModel> listaKanala = new ArrayList<>();

	static {
		listaKorisnika.add(new UserModel("korisnik1", "pass1"));
		listaKorisnika.add(new UserModel("korisnik2", "pass2"));
		listaKorisnika.add(new UserModel("korisnik3", "pass3"));
		listaKorisnika.add(new UserModel("korisnik4", "pass3"));
		listaKorisnika.add(new UserModel("korisnik5", "pass3"));

		listaKanala.add(new ChannelModel("kanal1", "room 1"));
		listaKanala.add(new ChannelModel("kanal2", "room 2"));
		listaKanala.add(new ChannelModel("kanal3", "room 3"));

	}

}
