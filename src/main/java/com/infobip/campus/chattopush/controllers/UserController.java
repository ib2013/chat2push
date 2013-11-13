package com.infobip.campus.chattopush.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.UserService;

import com.infobip.campus.chattopush.services.enums.StatusAction;
import com.infobip.campus.chattopush.services.enums.StatusUser;

@RequestMapping("/user/**")
@Controller
public class UserController {

	UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login", consumes = "application/json")
	@ResponseBody

	public StatusUser loginUser(@RequestBody UserModel model) {

		return userService.loginUser(model);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/register", consumes = "application/json")
	@ResponseBody

	public StatusUser registerUser(@RequestBody UserModel model) {
		return userService.registerUser(model);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/delete", consumes = "application/json")
	@ResponseBody

	public StatusAction deleteUser(@RequestBody UserModel model) {

		if (userService.deleteUser(model) == true) {
			return true;
		}
		return false;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/fetchAllUsers")
	@ResponseBody
	public List<UserModel> fetchAllUsers() {

		return userService.fetchAllUsers();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/addChannelToUser", consumes = "application/json")
	@ResponseBody
	public boolean addChannelToUser(@RequestBody UsersChannels _model) {

		return userService.addChannelToUser(_model);

	}

}
