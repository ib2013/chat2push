package com.infobip.campus.chattopush.services;

import org.springframework.stereotype.Service;

import com.infobip.campus.chattopush.models.UserModel;

@Service
public class DefaultUserService implements UserService {

	public boolean loginUser() {
		return false;
	}

	public boolean registerUser(UserModel model) {
		return false;
	}

}
