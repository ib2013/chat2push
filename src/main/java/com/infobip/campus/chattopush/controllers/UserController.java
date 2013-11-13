package com.infobip.campus.chattopush.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.services.DefaultUserService;

@RequestMapping("/user/**")
@Controller
public class UserController {

	@Autowired
	DefaultUserService defaultUserService;

	@RequestMapping(method = RequestMethod.POST, value = "/login", consumes = "application/json")
	@ResponseBody
	public String loginUser(@RequestBody UserModel model) {

		return defaultUserService.loginUser(model);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/register", consumes = "application/json")
	@ResponseBody
	public String registerUser(@RequestBody UserModel model) {

		if (defaultUserService.registerUser(model) == "success") {
			return "success";
		} else {
			return "exists";
		}

	}

	@RequestMapping(method = RequestMethod.POST, value = "/delete", consumes = "application/json")
	@ResponseBody

	public String deleteUser(@RequestBody UserModel model) {

		return defaultUserService.deleteUser(model);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/fetchAllUsers")
	@ResponseBody

	public List<UserModel> fetchAllUsers() {


		return defaultUserService.fetchAllUsers();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/addChannelToUser", consumes = "application/json")
	@ResponseBody
	public String addChannelToUser(@RequestBody String user_Channel) {

		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(user_Channel);
		return defaultUserService.addChannelToUser(object);

	}

	/* samo za testiranje, ne implementirati u aplikacije */
	@RequestMapping(method = RequestMethod.POST, value = "/purge")
	public boolean deleteAll() {
		defaultUserService.deleteAll();
		return true;
	}
}
