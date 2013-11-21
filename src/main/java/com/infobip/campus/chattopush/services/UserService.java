package com.infobip.campus.chattopush.services;

import java.util.List;
import java.util.Map;

import com.infobip.campus.chattopush.models.UserModel;

public interface UserService {

	public void loginUser(UserModel _model);

	public void registerUser(UserModel _model);

	public void verifyUser(UserModel _model);

	public void resendVerificationCode(UserModel _model);

	public void deleteUser(UserModel _model);

	public List<UserModel> fetchAllUsers();

	public Map<String, Integer> fetchUserStatistics(UserModel _model);

}
