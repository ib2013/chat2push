package com.infobip.campus.rsstopush.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/feed/**")
@Controller
public class FeedController {

	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public String addFeed() {
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/delete")
	public String deleteFeed() {
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/fetch")
	public String fetchFeed() {
		return null;
	}
	/*
	 * @ExceptionHandler(Exception.class) public ResponseEntity<String> handleException(Exception exception) { return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST); }
	 */
}