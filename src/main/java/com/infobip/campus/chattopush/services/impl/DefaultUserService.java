package com.infobip.campus.chattopush.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.infobip.campus.chattopush.configuration.MD5;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.UserService;
import com.infobip.campus.chattopush.services.enums.StatusAction;
import com.infobip.campus.chattopush.services.enums.StatusUser;

@Service
public class DefaultUserService implements UserService {

	public StatusUser loginUser(UserModel _model) {

		// TODO Auto-generated method stub
		List<UserModel> list = UserModel.findAllUserModels();

		for (UserModel model : list) {
			if (model.getUsername().contentEquals(_model.getUsername())) {
				if (model.getPassword().contentEquals(MD5.getMD5(_model.getPassword()))) {

					return StatusUser.SUCCESS;
				} else {
					return StatusUser.PASSERROR;
				}
			}
		}

		return StatusUser.NOUSER;
	}

	public StatusUser registerUser(UserModel _model) {

		// TODO Auto-generated method stub
		try {
			if (checkUserExists(_model) == false) {
				UserModel newUser = new UserModel();
				newUser.setUsername(_model.getUsername());
				newUser.setPassword(MD5.getMD5(_model.getPassword()));
				newUser.merge();
				return StatusUser.SUCCESS;
			}
			return StatusUser.EXISTS;
		} catch (Exception e) {
			// TODO: handle exception
			return StatusUser.EXC;

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

	public StatusAction deleteUser(UserModel _model) {

		// TODO Auto-generated method stub
		List<UserModel> list = UserModel.findAllUserModels();
		List<UsersChannels> listaKanala = UsersChannels.findAllUsersChannelses();

		boolean deleteUser = false;
		boolean deleteUserChannelRelation = false;

		try {
			for (UserModel modelUser : list) {
				if (modelUser.getUsername().contentEquals(_model.getUsername().toString())) {
					modelUser.remove();

					deleteUser = true;
				}
			}

			for (UsersChannels modelKanal : listaKanala) {
				if (modelKanal.getUsername().contentEquals(_model.getUsername())) {
					modelKanal.remove();
					deleteUserChannelRelation = true;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block

			return StatusAction.EXC;
		}

		if (deleteUserChannelRelation == true && deleteUser == true) {
			return StatusAction.SUCCESS;
		}

		return StatusAction.FAIL;

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

	public Map<String, Integer> fetchUserStatistics(UserModel _model) {
		// TODO Auto-generated method stub
		List<ChannelModel> channels = ChannelModel.findAllChannelModels();
		List<MessageModel> messages = MessageModel.findAllMessageModels();

		Map<String, Integer> statistic = new HashMap<String, Integer>();

		for (ChannelModel chnlModel : channels) {
			int brojPoruka = 0;
			for (MessageModel msgModel : messages) {
				if (msgModel.getChannel().contentEquals(chnlModel.getName()) && msgModel.getUser().contentEquals(_model.getUsername())) {
					brojPoruka++;
				}
			}
			statistic.put(chnlModel.getName(), brojPoruka);
		}

		return statistic;
	}
}
