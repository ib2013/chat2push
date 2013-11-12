package com.infobip.campus.chattopush.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.services.DefaultUserService;

@RequestMapping("/user/**")
@Controller
public class UserController {

	@Autowired
	DefaultUserService defaultUserService;

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	@ResponseBody
	public boolean loginUser(@RequestBody String username, String password) {
		return defaultUserService.loginUser(username, password);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register", consumes = "application/json")
	@ResponseBody
	public boolean registerUser(@RequestBody final UserModel model) {
		if (defaultUserService.registerUser() == true) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/test")
	@ResponseBody
	public UserModel testUser() {
		UserModel model = new UserModel();
		model.setGoogleId("asdfasdasdf");
		model.setPassword("jakk");
		model.setUsername("affdasfas");

		return model;
	}

}
