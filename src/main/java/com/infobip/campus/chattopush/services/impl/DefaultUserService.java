package com.infobip.campus.chattopush.services.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.infobip.campus.chattopush.configuration.MD5;
import com.infobip.campus.chattopush.database.MessageRepository;
import com.infobip.campus.chattopush.database.UserChannelsRepository;
import com.infobip.campus.chattopush.database.UserRepository;
import com.infobip.campus.chattopush.exceptions.CustomException;
import com.infobip.campus.chattopush.exceptions.ErrorCode;
import com.infobip.campus.chattopush.models.UserModel;
import com.infobip.campus.chattopush.models.UsersChannels;
import com.infobip.campus.chattopush.services.UserService;

@Service
public class DefaultUserService implements UserService {

	@PersistenceContext
	protected EntityManager em;

	UserRepository userRepository;
	UserChannelsRepository userChannelsRepository;
	MessageRepository messageRepository;

	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public void setUserChannelsRepository(UserChannelsRepository userChannelsRepository) {
		this.userChannelsRepository = userChannelsRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void loginUser(UserModel model) {
		UserModel object = null;
		try {
			object = userRepository.findByUsername(model.getUsername());
			if (object != null) {
				if (!MD5.getMD5(model.getPassword()).contentEquals(object.getPassword())) {
					throw new CustomException(ErrorCode.PASSERROR);
				}
			} else {
				throw new CustomException(ErrorCode.NOUSER);
			}
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void registerUser(UserModel model) {
		if (checkUserExists(model) == true) {
			throw new CustomException(ErrorCode.EXISTS);
		} else {
			UserModel newUser = new UserModel();
			try {
				newUser.setUsername(model.getUsername());
				newUser.setPassword(MD5.getMD5(model.getPassword()));
				newUser.setRegistrationCode(1000 + (int) (Math.random() * 9000));
				newUser.setRegistrationStatus(0);
				newUser.setPhoneNumber(model.getPhoneNumber());
				newUser.merge();
			} catch (Exception e) {
				throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@Override
	public void deleteUser(UserModel model) {
		if (checkUserExists(model) == true) {
			try {
				userRepository.deleteUser(model.getUsername());
				userRepository.removeFromAllChannels(model.getUsername());
			} catch (Exception ex) {
				throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@Override
	public List<UserModel> fetchAllUsers() {
		try {
			return UserModel.findAllUserModels();
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Map<String, Integer> fetchUserStatistics(UserModel model) {
		Collection<UsersChannels> subscribedChannels = userChannelsRepository.getSubscribedChannels(model.getUsername());

		Map<String, Integer> statistic = new HashMap<String, Integer>();

		for (UsersChannels uc : subscribedChannels) {
			int count = messageRepository.getMessagesForUserAndChannel(uc.getUsername(), uc.getChannel());
			statistic.put(uc.getChannel(), count);
		}

		return statistic;
	}

	@Override
	public void verifyUser(UserModel model) {
		UserModel um = userRepository.findByUsername(model.getUsername());

		if (um == null) {
			throw new CustomException(ErrorCode.NOUSER);
		}

		if (um.getRegistrationStatus() == 1) {
			return;
		}

		if (um.getRegistrationCode() != model.getRegistrationCode()) {
			throw new CustomException(ErrorCode.WRONG_REGISTRATION_CODE);
		}

		model.setRegistrationStatus(1);
		model.merge();
	}

	private boolean checkUserExists(UserModel model) {
		UserModel um = userRepository.findByUsername(model.getUsername());
		return um == null ? false : true;
	}
}
