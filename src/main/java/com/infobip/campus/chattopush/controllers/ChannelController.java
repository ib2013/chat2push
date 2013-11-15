package com.infobip.campus.chattopush.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.chattopush.clients.ClientChannelModel;
import com.infobip.campus.chattopush.clients.UserActivityModel;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.ChannelService;
import com.infobip.campus.chattopush.services.enums.StatusCode;
import com.infobip.campus.chattopush.services.exceptions.CustomException;

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
	public List<ClientChannelModel> fetchSubscribedChannels(
			@PathVariable("username") String username) {
		return channelService.fetchSubscribedChannels(username);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/fetchUsersByRoom", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<UserActivityModel> fetchUsersByChannel(
			@RequestBody final ChannelModel channel) {
		return channelService.fetchUserByChannel(channel);

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/fetchOpositeUsersByRoom", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<UserModel> fetchOpositeUsersByChannel(
			@RequestBody final ChannelModel channel) {
		return channelService.fetchOpositeUserByChannel(channel);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = "application/json")
	@ResponseBody
	public StatusCode addChannel(@RequestBody final ChannelModel model) {
		if (channelService.addChannel(model) == true) {
			return StatusCode.SUCCESS;
		} else {
			return StatusCode.INTERNALSERVERERROR;
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public StatusCode deleteChannel(@RequestBody final ChannelModel model) {

		if (channelService.deleteChannel(model) == true) {

			return StatusCode.SUCCESS;
		} else {
			return StatusCode.INTERNALSERVERERROR;
		}

	}

	@RequestMapping(method = RequestMethod.POST, value = "/addUserToRoom", consumes = "application/json")
	@ResponseBody
	public StatusCode addUserToChannel(@RequestBody final UsersChannels object) {
		
		if (channelService.addUserToRoom(object) == true) {
			return StatusCode.SUCCESS;
		} else {
			return StatusCode.INTERNALSERVERERROR;
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeUserFromRoom", consumes = "application/json")
	@ResponseBody
	public StatusCode removeUserFromChannel(@RequestBody final UsersChannels object) {
		if (channelService.removeUserFromRoom(object) == true) {
			return StatusCode.SUCCESS;
		} else {
			return StatusCode.INTERNALSERVERERROR;
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/channelStatistic")
	@ResponseBody
	public  Map<String, Integer> channelStatistic() {
		return channelService.channelStatistics();
	}
	
	@ExceptionHandler(CustomException.class)
	public @ResponseBody String errorHandler(CustomException ce, HttpServletResponse response) {
		response.setStatus(ce.getErrorCode());
		return ce.getErrorMessage();
	}

}
