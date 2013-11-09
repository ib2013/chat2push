package com.infobip.campus.rsstopush.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/cron")
@Controller
public class CronJobController {

	private static final Logger LOG = LoggerFactory.getLogger(CronJobController.class);

	@RequestMapping(value = "/doJob", method = RequestMethod.GET, headers = "X-AppEngine-Cron=true")
	public ResponseEntity<String> doJob() {
		LOG.info("Requesting JOB!");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
