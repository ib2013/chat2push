package com.infobip.campus.chattopush.services.mock;

import java.util.List;

import com.infobip.campus.chattopush.configuration.UserConfiguration;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.UserService;

public class UserServiceMock implements UserService {

	@Override
	public statusUser loginUser(UserModel _model) {
		// TODO Auto-generated method stub

		for (UserModel model : UserConfiguration.usrs) {
			if (model.getUsername().contentEquals(_model.getUsername())) {
				if (model.getPassword().contentEquals(_model.getPassword())) {
					return statusUser.SUCCESS;
				} else {
					return statusUser.PASSERROR;
				}
			}
		}

		return statusUser.NOUSER;
	}

	@Override
	public statusUser registerUser(UserModel _model) {
		// TODO Auto-generated method stub
		if (checkUserExists(_model) == false) {
			if (UserConfiguration.usrs.add(_model) == true) {
				return statusUser.SUCCESS;
			}
		}
		return statusUser.EXISTS;

	}

	@Override
	public statusAction deleteUser(UserModel _model) {
		// TODO Auto-generated method stub

		for (int i = 0; i < UserConfiguration.usrs.size(); i++) {
			if (UserConfiguration.usrs.get(i).getUsername().contentEquals(_model.getUsername())) {
				UserConfiguration.usrs.remove(i);
				return statusAction.SUCCESS;
			}
		}

		return statusAction.FAIL;
	}

	@Override
	public List<UserModel> fetchAllUsers() {
		// TODO Auto-generated method stub
		return UserConfiguration.usrs;
	}

	@Override
	public boolean addChannelToUser(UsersChannels _model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkUserExists(UserModel _model) {
		// TODO Auto-generated method stub
		for (UserModel model : UserConfiguration.usrs) {
			if (model.getUsername().contentEquals(_model.getUsername())) {
				return true;
			}
		}
		return false;
	}
}
