package com.infobip.campus.rsstopush.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.infobip.campus.rsstopush.services.FeedToPushService;

@RequestMapping("/cron")
@Controller
public class CronJobController {

	private static final Logger LOG = LoggerFactory.getLogger(CronJobController.class);

	@Autowired
	FeedToPushService feedToPushService;

	@RequestMapping(value = "/doJob", method = RequestMethod.GET, headers = "X-AppEngine-Cron=true")
	public ResponseEntity<String> doJob() {
		LOG.info("Requesting JOB!");

		feedToPushService.readRSSFeeds();

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
