package com.infobip.campus.chattopush.controllers;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.chattopush.clients.ClientMessageModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.services.MessageService;

@RequestMapping("/message/**")
@Controller
public class MessageController {

	MessageService messageService;

	public void setMessageService(MessageService mS) {
		this.messageService = mS;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/fetch/{username}/{channel}/{start-time}/{end-time}")
	@ResponseBody
	public List<MessageModel> fetchMessageList(

			@PathVariable("username") @NotNull String un,
			@PathVariable("channel") @NotNull String ch,
			@PathVariable("start-time") @NotNull long startTime,
			@PathVariable("end-time") @NotNull long endTime) {
		return messageService.fetchMessageList(un, ch, startTime, endTime);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/send")//, consumes = "application/json")
	@ResponseBody
	public boolean sendMessage(@RequestBody final ClientMessageModel msg) {
		
		return messageService.sendMessage(msg);
	}
}
