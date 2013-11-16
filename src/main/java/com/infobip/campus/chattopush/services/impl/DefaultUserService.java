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
import com.infobip.campus.chattopush.services.SmsMessageService;
import com.infobip.campus.chattopush.services.UserService;

@Service
public class DefaultUserService implements UserService {

	@PersistenceContext
	protected EntityManager em;

	SmsMessageService smsMessageService;
	UserRepository userRepository;
	UserChannelsRepository userChannelsRepository;
	MessageRepository messageRepository;

	public void setSmsMessageService(SmsMessageService smsMessageService) {
		this.smsMessageService = smsMessageService;
	}

	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public void setUserChannelsRepository(
			UserChannelsRepository userChannelsRepository) {
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
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		if (object != null) {
			if (!MD5.getMD5(model.getPassword()).contentEquals(
					object.getPassword())) {
				throw new CustomException(ErrorCode.PASSERROR);
			}
			if (object.getRegistrationStatus() == 0) {
				throw new CustomException(ErrorCode.MISSING_REGISTRATION);
			}
		} else {
			throw new CustomException(ErrorCode.NOUSER);
		}

	}

	@Override
	public void resendVerificationCode(UserModel model) {
		UserModel user = userRepository.findByUsername(model.getUsername());

		if (user == null) {
			throw new CustomException(ErrorCode.NOUSER);
		}

		try {
			smsMessageService.sendSmsMessage("Chat2Push",
					"C2P Registration code:" + user.getRegistrationCode(),
					user.getPhoneNumber());
		} catch (Exception e) {
			e.printStackTrace();
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
				smsMessageService.sendSmsMessage(
						"Chat2Push",
						"Chat2Push Registration code for user " + newUser.getUsername() + ":"
								+ newUser.getRegistrationCode(),
						newUser.getPhoneNumber());
				newUser.merge();
			} catch (Exception e) {
				e.printStackTrace();
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
		Collection<UsersChannels> subscribedChannels = null;
		try {
			subscribedChannels = userChannelsRepository
					.getSubscribedChannels(model.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}

		Map<String, Integer> statistic = new HashMap<String, Integer>();

		for (UsersChannels uc : subscribedChannels) {
			int count = messageRepository.getMessagesForUserAndChannel(
					uc.getUsername(), uc.getChannel());
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

		um.setRegistrationStatus(1);
		um.merge();
	}

	private boolean checkUserExists(UserModel model) {
		UserModel um = null;
		try {
			um = userRepository.findByUsername(model.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return um == null ? false : true;
	}
}
