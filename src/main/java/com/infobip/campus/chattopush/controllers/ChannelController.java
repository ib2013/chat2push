package com.infobip.campus.chattopush.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.services.DefaultChannelService;


@RequestMapping("/channel/**")
@Controller
public class ChannelController {

	@Autowired
	DefaultChannelService defaultChannelService;

	@RequestMapping(method = RequestMethod.GET, value = "/fetch")
	@ResponseBody
	public List<ChannelModel> fetchChannelList() {
		return defaultChannelService.fetchChannelList();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/fetch/{username}")
	@ResponseBody
	public String fetchSubscribedUserByChannelListService(@PathVariable ("username") String username) {
		return defaultChannelService.fetchSubscribedUserByChannelListService(username).toString();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/fetchUsersByRoom", consumes = "application/json")
	@ResponseBody
	public ArrayList<UserModel> fetchUsersByChannel(@RequestBody final ChannelModel channelName) {
		return defaultChannelService.fetchUserByChannel(channelName);
		
	}
	

	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = "application/json")
	@ResponseBody
	public String addChannel(@RequestBody final ChannelModel model) {

		if (defaultChannelService.addChannel(model) == true) {
			return "success";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String deleteChannel(@RequestBody final ChannelModel model) {
		
		if (defaultChannelService.deleteChannel(model) == true) {
			
			return "success";
		} else {
			return "false";
		}

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addUserToRoom", consumes = "application/json")
	@ResponseBody
	public String addUserToChannel(@RequestBody JsonObject object) {

		if (defaultChannelService.addUserToRoom(object) == true) {
			return "success";
		} else {
			return "fail";
		}
	}

}
