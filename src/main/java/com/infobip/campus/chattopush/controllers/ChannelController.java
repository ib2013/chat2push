package com.infobip.campus.chattopush.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.chattopush.models.ChannelModel;

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
}
