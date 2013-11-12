package com.infobip.campus.chattopush.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.chattopush.models.ChannelModel;
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
	public boolean deleteUser(@RequestBody UserModel model) {

		return defaultUserService.deleteUser(model);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/fetchAllUsers")
	@ResponseBody
	public boolean fetchAllUsers() {

		return defaultUserService.fetchAllUsers();

	}

	/* samo za testiranje, ne implementirati u aplikacije */
	@RequestMapping(method = RequestMethod.POST, value = "/purge")
	public boolean deleteAll() {
		defaultUserService.deleteAll();
		return true;
	}
}
