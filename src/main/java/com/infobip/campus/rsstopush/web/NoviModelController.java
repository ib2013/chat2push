package com.infobip.campus.rsstopush.web;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.rsstopush.models.NoviModel;

@RequestMapping(value = "/novimodel")
@Controller
public class NoviModelController {

	@RequestMapping(value = "/list")
	@ResponseBody
	public Collection<NoviModel> fetchModels() {
		return NoviModel.findAllNoviModels();
	}

}
