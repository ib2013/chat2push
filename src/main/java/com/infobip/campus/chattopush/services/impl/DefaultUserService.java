package com.infobip.campus.chattopush.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.UserService;

@Service
public class DefaultUserService implements UserService {

	public statusLoginUser loginUser(UserModel _model) {
		// TODO Auto-generated method stub
		List<UserModel> list = UserModel.findAllUserModels();

		for (UserModel model : list) {
			if (model.getUsername().contentEquals(_model.getUsername())) {
				if (model.getPassword().contentEquals(_model.getPassword())) {
					return statusLoginUser.SUCCESS;
				} else {
					return statusLoginUser.PASSERROR;
				}
			}
		}

		return statusLoginUser.NOUSER;
	}

	public statusLoginUser registerUser(UserModel _model) {
		// TODO Auto-generated method stub
		try {
			if (checkUserExists(_model) == false) {
				_model.merge();
				return statusLoginUser.SUCCESS;
			}
			return statusLoginUser.EXISTS;
		} catch (Exception e) {
			// TODO: handle exception
			return statusLoginUser.EXC;
		}

	}

	public String deleteAll() {
		// TODO Auto-generated method stub
		List<UserModel> list = UserModel.findAllUserModels();

		for (UserModel model : list) {
			model.remove();
		}
		return "success";
	}

	public boolean deleteUser(UserModel _model) {
		// TODO Auto-generated method stub
		List<UserModel> list = UserModel.findAllUserModels();

		try {
			for (UserModel model : list) {
				if (model.getUsername().contentEquals(_model.getUsername().toString())) {
					model.remove();
					return true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return false;

	}

	public boolean checkUserExists(UserModel _model) {
		List<UserModel> list = UserModel.findAllUserModels();

		for (UserModel model : list) {
			if (model.getUsername().contentEquals(_model.getUsername().toString())) {
				return true;
			}
		}

		return false;
	}

	public List<UserModel> fetchAllUsers() {
		// TODO Auto-generated method stub
		try {
			return UserModel.findAllUserModels();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public boolean addChannelToUser(UsersChannels _model) {
		// TODO Auto-generated method stub
		List<ChannelModel> channelModel = ChannelModel.findAllChannelModels();

		return false;
	}
}
