package com.infobip.campus.chattopush.services;

import java.util.List;

import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;

public interface UserService {

	public enum statusUser {
		SUCCESS, PASSERROR, NOUSER, EXISTS, EXC
	}

	public enum statusAction {
		SUCCESS, FAIL, EXC
	}

	public statusUser loginUser(UserModel _model);

	public statusUser registerUser(UserModel _model);

	public statusAction deleteUser(UserModel _model);

	public boolean checkUserExists(UserModel _model);

	public List<UserModel> fetchAllUsers();

	public boolean addChannelToUser(UsersChannels _model);
}
