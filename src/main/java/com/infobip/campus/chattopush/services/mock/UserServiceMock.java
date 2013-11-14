package com.infobip.campus.chattopush.services.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.infobip.campus.chattopush.configuration.UserConfiguration;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.models.UserModel;
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
		boolean deleteUserExecuted = false;
		boolean deleteUserChannelRelation = false;
		/*
		 * User delete
		 */
		for (int i = 0; i < UserConfiguration.usrs.size(); i++) {
			if (UserConfiguration.usrs.get(i).getUsername().contentEquals(_model.getUsername())) {
				UserConfiguration.usrs.remove(i);

				deleteUserExecuted = true;
			}
		}
		/*
		 * User relation delete
		 */
		for (int i = 0; i < UserConfiguration.us.size(); i++) {
			if (UserConfiguration.us.get(i).contentEquals(_model.getUsername())) {
				UserConfiguration.us.remove(i);
				UserConfiguration.cs.remove(i);
				deleteUserChannelRelation = true;
			}
		}

		if (deleteUserExecuted == true && deleteUserChannelRelation == true) {
			return StatusAction.SUCCESS;
		}

		return StatusAction.FAIL;
	}

	@Override
	public List<UserModel> fetchAllUsers() {
		// TODO Auto-generated method stub
		return UserConfiguration.usrs;
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

	@Override
	public Map<String, Integer> fetchUserStatistics(UserModel _model) {
		// TODO Auto-generated method stub
		Map<String, Integer> statistic = new HashMap<String, Integer>();

		for (ChannelModel chnlModel : UserConfiguration.chnls) {
			int brojPoruka = 0;
			for (MessageModel msgModel : UserConfiguration.msgs) {
				if (msgModel.getChannel().contentEquals(chnlModel.getName()) && msgModel.getUser().contentEquals(_model.getUsername())) {
					brojPoruka++;
				}
			}
			statistic.put(chnlModel.getName(), brojPoruka);
		}
		return statistic;
	}
}
