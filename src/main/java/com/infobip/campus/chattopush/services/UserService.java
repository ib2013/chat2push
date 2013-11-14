package com.infobip.campus.chattopush.services;

import java.util.List;
import java.util.Map;

import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.services.enums.StatusAction;
import com.infobip.campus.chattopush.services.enums.StatusUser;

public interface UserService {

	public StatusUser loginUser(UserModel _model);

	public StatusUser registerUser(UserModel _model);

	public StatusAction deleteUser(UserModel _model);

	public boolean checkUserExists(UserModel _model);

	public List<UserModel> fetchAllUsers();

	public Map<ChannelModel, Integer> fetchUserStatistics(UserModel _model);
}
