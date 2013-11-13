package com.infobip.campus.chattopush.services;

import java.util.List;

import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;

public interface UserService {

	public enum statusLoginUser {
		SUCCESS, PASSERROR, NOUSER, EXISTS, EXC
	}

	public statusLoginUser loginUser(UserModel _model);

	public statusLoginUser registerUser(UserModel _model);

	public boolean deleteUser(UserModel _model);

	public boolean checkUserExists(UserModel _model);

	public List<UserModel> fetchAllUsers();

	public boolean addChannelToUser(UsersChannels _model);
}
