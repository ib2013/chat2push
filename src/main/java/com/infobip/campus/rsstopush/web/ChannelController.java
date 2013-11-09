package com.infobip.campus.rsstopush.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.rsstopush.channels.ChannelModel;
import com.infobip.campus.rsstopush.channels.DefaultChannelService;

@RequestMapping("/channel/**")
@Controller
public class ChannelController {

	@Autowired
	DefaultChannelService defaultChannelService;

	@RequestMapping(method = RequestMethod.GET, value = "/fetch")
	@ResponseBody
	public List<ArrayList<ChannelModel>> fetchChannelList() {

		return Arrays.asList(defaultChannelService.fetchChannelList());

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String addChannel(@RequestBody ChannelModel model) {

		if (defaultChannelService.addChannel(model) == true) {
			return "success";
		} else {
			return "fail";
		}

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteChannel(@RequestBody String value) {
		ChannelModel model = new ChannelModel(value, "");

		if (defaultChannelService.deleteChannel(model) == true) {
			return "success";
		} else {
			return "false";
		}

	}
}
