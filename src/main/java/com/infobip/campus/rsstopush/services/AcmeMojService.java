package com.infobip.campus.rsstopush.services;

import org.springframework.stereotype.Service;

@Service
public class AcmeMojService implements MojService{

	@Override
	public void doSomething() {
		System.out.println("doing....");
	}

}
