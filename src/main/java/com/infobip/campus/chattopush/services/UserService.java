package com.infobip.campus.chattopush.services;

import java.util.List;
import java.util.Map;

import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.services.enums.StatusCode;

public interface UserService {

	public StatusCode loginUser(UserModel _model);

	public StatusCode registerUser(UserModel _model) throws Exception;

	public StatusCode deleteUser(UserModel _model);

	public boolean checkUserExists(UserModel _model);

	public List<UserModel> fetchAllUsers();

	public Map<String, Integer> fetchUserStatistics(UserModel _model);
}
