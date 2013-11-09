package com.infobip.campus.rsstopush.web;

import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/example/**")
@Controller
public class ExampleController {

	@RequestMapping(method = RequestMethod.POST, value = "{id}")
	public void post(@PathVariable Long id, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
	}

	@RequestMapping(value = "/stringovi", method = RequestMethod.GET)
	@ResponseBody
	public Collection<String> stringovi() {
		return Arrays.asList("prvi", "drugi", "treci");
	}

	@RequestMapping(value = "/dajMiView")
	public String dajMiView() {
		return "mojView";
	}

}