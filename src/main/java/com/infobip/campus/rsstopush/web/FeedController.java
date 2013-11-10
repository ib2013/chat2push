package com.infobip.campus.rsstopush.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.rsstopush.models.RssFeedModel;

@RequestMapping("/feed/**")
@Controller
public class FeedController {

	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = "application/json")
	@ResponseBody
	public String addFeed(@RequestBody RssFeedModel model) {
		try {
			model.persist();
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "false";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/delete", consumes = "application/json")
	@ResponseBody
	public String deleteFeed(@RequestBody RssFeedModel model) {
		try {
			model.remove();
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "false";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/fetch")
	@ResponseBody
	public List<List<RssFeedModel>> fetchFeed() {
		return Arrays.asList(RssFeedModel.findAllRssFeedModels());
	}
	/*
	 * @ExceptionHandler(Exception.class) public ResponseEntity<String> handleException(Exception exception) { return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST); }
	 */
}