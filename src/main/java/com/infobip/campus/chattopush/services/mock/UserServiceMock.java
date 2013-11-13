package com.infobip.campus.chattopush.services.mock;

import java.util.List;

import com.infobip.campus.chattopush.configuration.UserConfiguration;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.UserService;
import com.infobip.campus.chattopush.services.enums.StatusAction;
import com.infobip.campus.chattopush.services.enums.StatusUser;

public class UserServiceMock implements UserService {

	@Override
	public StatusUser loginUser(UserModel _model) {

		// TODO Auto-generated method stub

		for (UserModel model : UserConfiguration.usrs) {
			if (model.getUsername().contentEquals(_model.getUsername())) {
				if (model.getPassword().contentEquals(_model.getPassword())) {

					return StatusUser.SUCCESS;
				} else {
					return StatusUser.PASSERROR;

				}
			}
		}

		return StatusUser.NOUSER;
	}

	@Override
	public StatusUser registerUser(UserModel _model) {
		// TODO Auto-generated method stub
		if (checkUserExists(_model) == false) {
			if (UserConfiguration.usrs.add(_model) == true) {
				return StatusUser.SUCCESS;
			}
		}
		return StatusUser.EXISTS;

	}

	@Override
	public StatusAction deleteUser(UserModel _model) {

		// TODO Auto-generated method stub

		for (int i = 0; i < UserConfiguration.usrs.size(); i++) {
			if (UserConfiguration.usrs.get(i).getUsername()
					.contentEquals(_model.getUsername())) {
				UserConfiguration.usrs.remove(i);

				return StatusAction.SUCCESS;
			}
		}

		return StatusAction.FAIL;
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
