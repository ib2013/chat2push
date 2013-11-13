package com.infobip.campus.chattopush.services;

import java.util.List;

import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;

public interface UserService {

	public String loginUser(UserModel _model);

	public String registerUser(UserModel _model);

	public String deleteUser(UserModel _model);

	public String checkUserExists(UserModel _model);

	public List<UserModel> fetchAllUsers();

	public String addChannelToUser(UsersChannels _model);
}
