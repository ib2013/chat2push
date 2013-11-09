package com.infobip.campus.rsstopush.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> e15eedf3f2a67cc14df3c9b160f5ba12701eadc3
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

<<<<<<< HEAD
import com.infobip.campus.rsstopush.services.FeedToPushService;

=======
>>>>>>> e15eedf3f2a67cc14df3c9b160f5ba12701eadc3
@RequestMapping("/cron")
@Controller
public class CronJobController {

	private static final Logger LOG = LoggerFactory.getLogger(CronJobController.class);
<<<<<<< HEAD
	
	@Autowired
	FeedToPushService feedToPushService;
=======
>>>>>>> e15eedf3f2a67cc14df3c9b160f5ba12701eadc3

	@RequestMapping(value = "/doJob", method = RequestMethod.GET, headers = "X-AppEngine-Cron=true")
	public ResponseEntity<String> doJob() {
		LOG.info("Requesting JOB!");
<<<<<<< HEAD
		feedToPushService.readRSSFeeds();
=======
>>>>>>> e15eedf3f2a67cc14df3c9b160f5ba12701eadc3
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
