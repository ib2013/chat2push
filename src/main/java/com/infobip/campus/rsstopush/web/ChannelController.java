package com.infobip.campus.rsstopush.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.rsstopush.channels.ChannelHandler;
import com.infobip.campus.rsstopush.channels.ChannelModel;

@RequestMapping("/channel/**")
@Controller
public class ChannelController {

	@RequestMapping(method = RequestMethod.GET, value = "/fetch")
	@ResponseBody
	public List<ArrayList<ChannelModel>> fetchChannelList() {
		/* zasada null */
		ChannelHandler handler = new ChannelHandler();
		return Arrays.asList(handler.fetchChannelList());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String addChannel(@RequestBody ChannelModel model) {
		ChannelHandler handler = new ChannelHandler();
		if (handler.addChannel(model) == true) {
			return "success";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteChannel(@RequestBody String value) {
		ChannelModel model = new ChannelModel(value, "");
		ChannelHandler handler = new ChannelHandler();

		if (handler.deleteChannel(model) == true) {
			return "success";
		} else {
			return "false";
		}
	}
}
