package com.infobip.campus.chattopush.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.chattopush.clients.ClientMessageModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.services.MessageService;
import com.infobip.campus.chattopush.services.PushNotification;

@RequestMapping("/message/**")
@Controller
public class MessageController {

	MessageService messageService;

	public void setMessageService(MessageService mS) {
		this.messageService = mS;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/fetch/{username}/{channel}")/*{start-time}/{end-time}*/
	@ResponseBody
	public List<MessageModel> fetchMessageList(
			@PathVariable("username") String un,
			@PathVariable("channel") String ch)
			//@PathVariable("start-time") String startTime,
			//@PathVariable("end-time") String endTime) 
			{

		List<MessageModel> messages = messageService.fetchMessageList();
		List<MessageModel> result = new ArrayList<MessageModel>();
		//Date date1 = new Date(startTime);
		//Date date2 = new Date(endTime);

		for (MessageModel msg : messages) {
			if (msg.getChannel().equals(ch)
					//&& msg.getLastMessageDate().after(date1)
					//&& msg.getLastMessageDate().before(date2)
					&& msg.getUser().equals(un)) {
				result.add(msg);
			}
		}

		return result;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/send", consumes = "application/json")
	@ResponseBody
	public boolean sendMessage(@RequestBody final ClientMessageModel msg) {

		MessageModel mmodel = new MessageModel();

		String username = msg.getUsername();
		String channel = msg.getChannel();
		String message = msg.getMessageText();
		Date date = new Date();

		mmodel.setChannel(channel);
		mmodel.setMessage(message);
		mmodel.setUser(username);
		mmodel.setLastMessageDate(date);

		try {
			if (messageService.addMessage(mmodel)) {
				/*try {
					PushNotification pN = new PushNotification(mmodel);
					pN.notifyChannel();
				} catch (Exception e) {
					List<MessageModel> msgs = messageService.fetchMessageList();
					for (MessageModel m : msgs) {
						if (m.getChannel().equals(channel)
								&& m.getUser().equals(username)
								&& m.getMessage().equals(message)
								&& m.getLastMessageDate().equals(date)) {
							m.remove();
						}
					}
					e.printStackTrace();
					return false;
				}*/
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
