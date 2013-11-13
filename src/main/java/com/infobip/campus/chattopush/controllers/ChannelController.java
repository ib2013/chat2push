package com.infobip.campus.chattopush.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.services.ChannelService;


@RequestMapping("/channel/**")
@Controller
public class ChannelController {

	ChannelService channelService;
	
	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/fetch")
	@ResponseBody
	public List<ChannelModel> fetchChannelList() {
		return channelService.fetchChannelList();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/fetch/{username}")
	@ResponseBody
	public List<ChannelModel> fetchSubscribedChannels(@PathVariable ("username") String username) {
		return channelService.fetchSubscribedChannels(username);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/fetchUsersByRoom", consumes = "application/json", produces="application/json")
	@ResponseBody
	public List<UserModel> fetchUsersByChannel(@RequestBody final ChannelModel channelName) {
		return channelService.fetchUserByChannel(channelName);
		
	}
	

	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = "application/json")
	@ResponseBody
	public String addChannel(@RequestBody final ChannelModel model) {

		if (channelService.addChannel(model) == true) {
			return "success";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String deleteChannel(@RequestBody final ChannelModel model) {
		
		if (channelService.deleteChannel(model) == true) {
			
			return "success";
		} else {
			return "false";
		}

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addUserToRoom", consumes = "application/json")
	@ResponseBody
	public String addUserToChannel(@RequestBody String object) {
		//JsonObject obj = new JsonObject(object.);
		JsonParser jsonParser = new JsonParser();
		JsonObject obj = (JsonObject) jsonParser.parse(object);
		
		if (channelService.addUserToRoom(obj) == true) {
			return "success";
		} else {
			return "fail";
		}
	}


}
