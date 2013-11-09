package com.infobip.campus.rsstopush.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.rsstopush.channels.ChannelModel;
import com.infobip.campus.rsstopush.channels.DefaultChannelService;
import com.infobip.campus.rsstopush.services.DefaultFeedToPushService;

@RequestMapping("/channel/**")
@Controller
public class ChannelController {

	@Autowired
	DefaultChannelService defaultChannelService;
	@Autowired
	DefaultFeedToPushService defaultFeedToPush;

	@RequestMapping(method = RequestMethod.GET, value = "/fetch")
	@ResponseBody
	public List<Map<String, Object>> fetchChannelList() {
		List<Map<String, Object>> resultArray = new ArrayList<>();
		/*
		 * [{ channelModel : { name : "" descritpion : "" }, counter : }]
		 */

		Map<ChannelModel, Integer> channelMapCounterToJson = defaultFeedToPush.channelMapCounterToJson();

		for (Map.Entry<ChannelModel, Integer> entry : channelMapCounterToJson.entrySet()) {
			Map<String, Object> map = new HashMap<>();
			map.put("channelModel", entry.getKey());
			map.put("counter", entry.getValue());

			resultArray.add(map);
		}

		return resultArray;
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
