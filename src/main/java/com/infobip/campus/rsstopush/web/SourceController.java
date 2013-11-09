package com.infobip.campus.rsstopush.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/source/**")
@Controller
public class SourceController {

	@RequestMapping(method = RequestMethod.POST, value = "/add")
	@ResponseBody
	public String addSource() {
		return String.valueOf(true);
	}
}
