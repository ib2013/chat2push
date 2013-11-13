package com.infobip.campus.chattopush.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.mail.MailService.Message;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.services.DefaultMessageService;
import com.infobip.campus.chattopush.services.PushNotification;

@RequestMapping("/message/**")
@Controller
public class MessageController {
	@Autowired
	DefaultMessageService defaultMessageService;
	
	@Autowired
	DefaultMessageService defaultChannelService;

	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/fetch", params = {
	 * "username", "channel", "start-time", "end-time" }) public @ResponseBody
	 * JsonArray fetchMessageList(
	 * 
	 * @RequestParam(value = "username") String un,
	 * 
	 * @RequestParam(value = "channel") String ch,
	 * 
	 * @RequestParam(value = "start-time") String startTime,
	 * 
	 * @RequestParam(value = "end-time") String endTime) {
	 * 
	 * 
	 * JsonArray jsonArray = new JsonArray(); List<MessageModel> messages =
	 * defaultMessageService.fetchMessageList(); Date date1 = new
	 * Date(startTime); Date date2 = new Date(endTime);
	 * 
	 * for (MessageModel msg : messages) { if
	 * (msg.getChannel().getName().equals(ch) &&
	 * msg.getLastMessageDate().after(date1) &&
	 * msg.getLastMessageDate().before(date2)) { JsonObject jsonObject = new
	 * JsonObject(); jsonObject.addProperty("message-text", msg.getMessage());
	 * jsonObject.addProperty("sent-by", msg.getUser().getUsername());
	 * jsonObject.addProperty("time", msg.getLastMessageDate().toString());
	 * 
	 * jsonArray.add(jsonObject); } } return jsonArray; }
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/fetch/{username}/{channel}/{start-time}/{end-time}")
	@ResponseBody
	public JsonArray fetchMessageList(@PathVariable("username") String un,
			@PathVariable("channel") String ch,
			@PathVariable("start-time") String startTime,
			@PathVariable("end-time") String endTime) {

		JsonArray jsonArray = new JsonArray();
		List<MessageModel> messages = defaultMessageService.fetchMessageList();
		Date date1 = new Date(startTime);
		Date date2 = new Date(endTime);

		for (MessageModel msg : messages) {
			if (msg.getChannel().getName().equals(ch)
					&& msg.getLastMessageDate().after(date1)
					&& msg.getLastMessageDate().before(date2)) {
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("message-text", msg.getMessage());
				jsonObject.addProperty("sent-by", msg.getUser().getUsername());
				jsonObject.addProperty("time", msg.getLastMessageDate()
						.toString());

				jsonArray.add(jsonObject);
			}
		}
		return jsonArray;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/send", consumes = "application/json")
	@ResponseBody
	public JsonObject sendMessage(
		@RequestBody String body){
		
		MessageModel mmodel = new MessageModel();
		ChannelModel cmodel = new ChannelModel();
		UserModel umodel = new UserModel();
		
		List<ChannelModel> channels = ChannelModel.findAllChannelModels();
		for (ChannelModel c : channels) {
			if (c.getName().equals(ch)) {
				cmodel = c;
				break;
			}
		}
		List<UserModel> users = UserModel.findAllUserModels();
		for (UserModel u : users) {
			if (u.getUsername().equals(un)) {
				umodel = u;
				break;
			}
		}
		mmodel.setChannel(cmodel);
		mmodel.setMessage(message);
		mmodel.setUser(umodel);
		mmodel.setLastMessageDate(new Date());
		
		JsonObject response = new JsonObject();
		
		if (defaultMessageService.addMessage(mmodel) == true) {
			PushNotification pN = new PushNotification(mmodel);
			pN.notifyChannel();
			response.addProperty("status", "success");
			return response;
		} else{
			response.addProperty("status", "error");
			return response;
		}
	}
}
