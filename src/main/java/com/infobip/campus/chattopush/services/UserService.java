package com.infobip.campus.chattopush.services;

import java.util.List;
import java.util.Map;

import com.infobip.campus.chattopush.models.UserModel;

public interface UserService {

	public void loginUser(UserModel model);

	public void registerUser(UserModel model);

	public void verifyUser(UserModel model);
	
	public void resendVerificationCode(UserModel model);

	public void deleteUser(UserModel model);

	public List<UserModel> fetchAllUsers();

	public Map<String, Integer> fetchUserStatistics(UserModel model);
}
