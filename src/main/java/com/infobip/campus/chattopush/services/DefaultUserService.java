package com.infobip.campus.chattopush.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infobip.campus.chattopush.models.UserModel;

@Service
public class DefaultUserService implements UserService {

	@Override
	public boolean loginUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerUser() {
		// TODO Auto-generated method stub
		return false;
	}

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

	public String registerUser(UserModel model) {
		// TODO Auto-generated method stub
		try {
			if (checkUserExists(model) == false) {
				model.persist();
				return "success";
			}
			return "exists";
		} catch (Exception e) {
			// TODO: handle exception
			return e.toString();
		}

	}

	public boolean deleteAll() {
		// TODO Auto-generated method stub
		List<UserModel> list = UserModel.findAllUserModels();

		for (UserModel model : list) {
			model.remove();
		}
		return false;
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

}
