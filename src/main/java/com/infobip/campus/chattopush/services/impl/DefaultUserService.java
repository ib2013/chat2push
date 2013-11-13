package com.infobip.campus.chattopush.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.UserService;

@Service
public class DefaultUserService implements UserService {

	public String loginUser(UserModel _model) {
		// TODO Auto-generated method stub
		List<UserModel> list = UserModel.findAllUserModels();

		for (UserModel model : list) {
			if (model.getUsername().contentEquals(_model.getUsername())) {
				if (model.getPassword().contentEquals(_model.getPassword())) {
					return "success";
				} else {
					return "PwdError";
				}
			}
		}

		return "UserError";
	}

	public String registerUser(UserModel _model) {
		// TODO Auto-generated method stub
		try {
			if (checkUserExists(_model) == "fail") {
				_model.merge();
				return "success";
			}
			return "exists";
		} catch (Exception e) {
			// TODO: handle exception
			return e.toString();
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

	public String deleteUser(UserModel _model) {
		// TODO Auto-generated method stub
		List<UserModel> list = UserModel.findAllUserModels();

		try {
			for (UserModel model : list) {
				if (model.getUsername().contentEquals(_model.getUsername().toString())) {
					model.remove();
					return "success";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "fail";
		}
		return "fail";

	}

	public String checkUserExists(UserModel _model) {
		List<UserModel> list = UserModel.findAllUserModels();

		for (UserModel model : list) {
			if (model.getUsername().contentEquals(_model.getUsername().toString())) {
				return "success";
			}
		}

		return "fail";
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

	public String addChannelToUser(UsersChannels _model) {
		// TODO Auto-generated method stub
		List<ChannelModel> channelModel = ChannelModel.findAllChannelModels();

		return null;
	}
}
