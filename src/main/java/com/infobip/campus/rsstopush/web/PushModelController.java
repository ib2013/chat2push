package com.infobip.campus.rsstopush.web;

import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infobip.campus.rsstopush.models.PushModel;
import com.infobip.campus.rsstopush.services.MojService;

@RequestMapping("/pushmodel/**")
@Controller
public class PushModelController {

	@Autowired
	private MojService mojService;

	@RequestMapping(method = RequestMethod.POST, value = "{id}")
	public void post(@PathVariable Long id, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	@ResponseBody
	public Collection<PushModel> list() {
		return PushModel.findAllPushModels();
	}

	@RequestMapping(value = "/create/{title}")
	@ResponseBody
	public PushModel create(@PathVariable("title") String title) {
		PushModel model = new PushModel();
		model.setTitle(title);
		return model.merge();
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

	@RequestMapping("/pozoviService")
	public void pozoviService() {
		mojService.doSomething();
	}
}
