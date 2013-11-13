package com.infobip.campus.chattopush.services.mock;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.infobip.campus.chattopush.clients.ClientChannelModel;
import com.infobip.campus.chattopush.clients.UserActivityModel;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.ChannelService;

public class ChannelServiceMock implements ChannelService {
	
	List<ChannelModel> list = new ArrayList<ChannelModel>();
	List<UserModel> users = new ArrayList<UserModel>();
	
	List<String> usr = new ArrayList<String>();
	List<String> chl = new ArrayList<String>();
	
	public ChannelServiceMock() {
		list.add(new ChannelModel("Soba za odmor","room 1"));
		list.add(new ChannelModel("Kuca za odmor","room 2"));
		list.add(new ChannelModel("Kafana","cofee"));
		
		users.add(new UserModel("user1","user1"));
		users.add(new UserModel("user2","user2"));
		users.add(new UserModel("user3","user3"));
	}
	
	@Override
	public List<ChannelModel> fetchChannelList() {
		return list;
	}

	@Override
	public boolean addChannel(ChannelModel channel) {
		list.add(channel);
		return false;
	}

	@Override
	public boolean deleteChannel(ChannelModel channel) {
		for (ChannelModel ch : list){
			if(ch.getName().equals(channel.getName())){
				list.remove(ch);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateChannel(ChannelModel oldModel, ChannelModel newModel) {
		for (ChannelModel ch : list){
			if(ch.getName().equals(oldModel.getName())){
				list.remove(ch);
				list.add(newModel);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<ClientChannelModel> fetchSubscribedChannels(String username) {
		List<ClientChannelModel> clientList = new ArrayList<ClientChannelModel>();
		
		for (ChannelModel ch : list){
			ClientChannelModel clChM = new ClientChannelModel();
			clChM.setDescription(ch.getDescription());
			clChM.setName(ch.getName());
			clChM.setPublic(ch.isIsPublic());
			clChM.setSubscribed(Math.random() < 0.5 ? true : false);
		}
		return null;
	}

	@Override
	public boolean addUserToRoom(UsersChannels object) {
		usr.add(object.getUsername());
		chl.add(object.getChannel());
		
		return true;
	}
	
	@Override
	public boolean removeUserFromRoom(UsersChannels object) {
		for (int i=0; i<usr.size(); i++){
			if (usr.get(i).equals(object.getUsername()) && chl.get(i).equals(object.getChannel())){
				usr.remove(i);
				chl.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<UserActivityModel> fetchUserByChannel(ChannelModel channelName) {
		// TODO Auto-generated method stub
		List<UserActivityModel> uAcM = new ArrayList<UserActivityModel>();
		for (int i=0; i<usr.size(); i++){
			if (chl.get(i).equals(channelName)){
				UserActivityModel x = new UserActivityModel();
				x.setUsername(usr.get(i));
				x.setMessageCount((int)Math.random()*100);
				uAcM.add(x);
			}
		}
		return uAcM;
	}

}
