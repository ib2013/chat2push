package com.infobip.campus.chattopush.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.chattopush.exceptions.CustomException;
import com.infobip.campus.chattopush.exceptions.ErrorCode;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.services.UserService;

@RequestMapping("/user/**")
@Controller
public class UserController {

	UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login", consumes = "application/json")
	@ResponseBody
	public void loginUser(@RequestBody UserModel model) {
		userService.loginUser(model);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register", consumes = "application/json")
	@ResponseBody
	public void registerUser(@RequestBody UserModel model) {
		userService.registerUser(model);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/verify", consumes = "application/json")
	@ResponseBody
	public ErrorCode verifyUser(@RequestBody UserModel model) {
		return userService.verifyUser(model);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/delete", consumes = "application/json")
	@ResponseBody
	public void deleteUser(@RequestBody UserModel model) {

		userService.deleteUser(model);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/fetchAllUsers")
	@ResponseBody
	public List<UserModel> fetchAllUsers() {

		return userService.fetchAllUsers();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/fetchUserStatistics", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Integer> fetchUserStatistics(@RequestBody UserModel _model) {
		return userService.fetchUserStatistics(_model);
	}

	@ExceptionHandler(CustomException.class)
	public @ResponseBody
	String errorHandler(CustomException ce, HttpServletResponse response) {
		response.setStatus(ce.getErrorCode());
		return ce.getErrorMessage();
	}
}
