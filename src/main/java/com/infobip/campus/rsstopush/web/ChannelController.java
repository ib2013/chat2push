package com.infobip.campus.rsstopush.web;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/channel/**")
@Controller
public class ChannelController {

	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseBody
	public Collection<String> fetchChannelList(){
		/*zasada null*/
		return Arrays.asList("test","test");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String addChannel(){
		return String.valueOf(true);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteChannel(){
		return String.valueOf(false);
	}
}
