package com.infobip.campus.chattopush.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.infobip.campus.chattopush.configuration.MD5;
import com.infobip.campus.chattopush.database.UserRepository;
import com.infobip.campus.chattopush.exceptions.CustomException;
import com.infobip.campus.chattopush.exceptions.ErrorCode;
import com.infobip.campus.chattopush.models.ChannelModel;
import com.infobip.campus.chattopush.models.MessageModel;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.services.UserService;

@Service
public class DefaultUserService implements UserService {

	@PersistenceContext
	protected EntityManager em;

	UserRepository userRepository;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void loginUser(UserModel model) {
		UserModel object = null;

		try {
			object = userRepository.loginUser(model);
			if (object != null) {
				if (model.equals(object)) {
					throw new CustomException(ErrorCode.EXISTS);
				} else {
					if (!model.getUsername().contentEquals(object.getUsername())) {
						throw new CustomException(ErrorCode.NOUSER);
					} else if (!MD5.getMD5(model.getPassword()).contentEquals(object.getPassword())) {
						throw new CustomException(ErrorCode.PASSERROR);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void registerUser(UserModel _model) {

		// TODO Auto-generated method stub
		if (checkUserExists(_model) == true) {
			throw new CustomException(ErrorCode.EXISTS);
		} else {
			UserModel newUser = new UserModel();
			try {
				newUser.setUsername(_model.getUsername());
				newUser.setPassword(MD5.getMD5(_model.getPassword()));
				newUser.setRegistrationCode(1000 + (int) (Math.random() * 9000));
				newUser.setRegistrationStatus(0);
				newUser.setPhoneNumber(_model.getPhoneNumber());
				newUser.merge();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
			}
		}
		throw new CustomException(ErrorCode.SUCCESS);
	}

	public void deleteAll() throws CustomException {
		// TODO Auto-generated method stub
		List<UserModel> lista = UserModel.findAllUserModels();

		try {
			for (UserModel model : lista) {
				model.remove();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void deleteUser(UserModel model) {

		// TODO Auto-generated method stub
		if (checkUserExists(model) == true) {
			try {
				userRepository.deleteUser(model);
				userRepository.removeFromAllChannels(model);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
			}
		} else {
			throw new CustomException(ErrorCode.NOUSER);
		}
	}

	@Override
	public List<UserModel> fetchAllUsers() {
		// TODO Auto-generated method stub
		try {
			return UserModel.findAllUserModels();
		} catch (Exception e) {
			// TODO: handle exception
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public Map<String, Integer> fetchUserStatistics(UserModel _model) {
		// TODO Auto-generated method stub
		List<ChannelModel> channels = ChannelModel.findAllChannelModels();
		List<MessageModel> messages = MessageModel.findAllMessageModels();

		Map<String, Integer> statistic = new HashMap<String, Integer>();

		for (ChannelModel chnlModel : channels) {
			int brojPoruka = 0;
			for (MessageModel msgModel : messages) {
				if (msgModel.getChannel().contentEquals(chnlModel.getName()) && msgModel.getUsername().contentEquals(_model.getUsername())) {
					brojPoruka++;
				}
			}
			statistic.put(chnlModel.getName(), brojPoruka);
		}

		return statistic;
	}

	@Override
	public ErrorCode verifyUser(UserModel _model) {
		List<UserModel> list = UserModel.findAllUserModels();

		for (UserModel model : list) {
			if (model.getUsername().contentEquals(_model.getUsername())) {
				if (model.getRegistrationStatus() != 0) {
					return ErrorCode.EXC;
				} else if (model.getRegistrationCode() != _model.getRegistrationCode()) {
					return ErrorCode.WRONG_REGISTRATION_CODE;
				} else {
					model.setRegistrationStatus(1);
					model.merge();
					return ErrorCode.SUCCESS;
				}
			}
		}

		return ErrorCode.NOUSER;
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
}
