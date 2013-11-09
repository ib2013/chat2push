package com.infobip.campus.rsstopush.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.rsstopush.models.RssSourceModel;

@RequestMapping("/source/**")
@Controller
public class SourceController {

	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = "application/json")
	@ResponseBody
	public String addSource(@RequestBody RssSourceModel model) {
		try {
			model.persist();
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "fail";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/fetch")
	@ResponseBody
	public List<List<RssSourceModel>> fetchSource() {
		return Arrays.asList(RssSourceModel.findAllRssSourceModels());
	}
}
