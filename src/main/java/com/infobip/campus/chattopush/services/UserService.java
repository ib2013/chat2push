package com.infobip.campus.chattopush.services;

import java.util.List;
import java.util.Map;

import com.infobip.campus.chattopush.exceptions.ErrorCode;
import com.infobip.campus.chattopush.models.UserModel;

public interface UserService {

	public ErrorCode loginUser(UserModel _model);

	public ErrorCode registerUser(UserModel _model) throws Exception;

	public ErrorCode deleteUser(UserModel _model);

	public boolean checkUserExists(UserModel _model);

	public List<UserModel> fetchAllUsers();

	public Map<String, Integer> fetchUserStatistics(UserModel _model);
}
