package com.infobip.campus.chattopush.controllers;

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

	DefaultUserService defaultUserService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	@ResponseBody
	public boolean loginUser(@RequestBody final UserModel model) {
		
		if (defaultUserService.loginUser() == true) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	@ResponseBody
	public boolean registerUser(@RequestBody final UserModel model) {
		
		if (defaultUserService.registerUser(model) == true) {
			return true;
		} else {
			return false;
		}
	}

}
