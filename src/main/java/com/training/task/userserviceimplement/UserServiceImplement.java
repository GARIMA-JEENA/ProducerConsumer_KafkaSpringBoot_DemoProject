package com.training.task.userserviceimplement;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.training.task.constants.Constants;
import com.training.task.dao.UserDao;
import com.training.task.entity.User;
import com.training.task.exception.UserInvalidInputException;
import com.training.task.exception.UserNotFoundException;
import com.training.task.message.Message;
import com.training.task.message.UserDto;
import com.training.task.userservice.UserService;
import com.training.task.util.UserUtil;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	KafkaTemplate<String, Message> kafkaTemplate;

	private static final String TOPIC = "TrainingTask3";

	@Override
	public List<UserDto> getAllUsers(Pageable page) {
		Page<User> pagedResult = userDao.findAll(page);
		List<User> l = pagedResult.getContent();
		return UserUtil.convertUserToDto(l);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> user = userDao.findAll();
		return UserUtil.convertUserToDto(user);
	}

	@Override
	public UserDto getUser(String rollNumber) {
		User user = userDao.findById(rollNumber).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("User with Given Id does Not exists.");
		}
		return UserUtil.convertUserToDto(user);
	}

	@Override
	public void createUser(UserDto userDto) {
		if (userDto.getRollNumber().isEmpty() || userDto.getUserName().isEmpty() || userDto.getStream().isEmpty()) {
			throw new UserInvalidInputException("Input fields are Empty please check.");
		}
		Message message = new Message();
		message.setAction(Constants.CREATE);
		message.userDto = userDto;
		kafkaTemplate.send(TOPIC, message);
	}

	@Override
	public void updateUser(UserDto userDto) {
		if (userDto.getRollNumber().isEmpty() || userDto.getUserName().isEmpty() || userDto.getStream().isEmpty()) {
			throw new UserInvalidInputException("Input fields are Empty please check.");
		}
		Message message = new Message();
		message.setAction(Constants.UPDATE);
		message.setUserDto(userDto);
		kafkaTemplate.send(TOPIC, message);
	}

	@Override
	public void deleteUser(String rollNumber) {
		if (userDao.existsById(rollNumber) == false) {
			throw new UserNotFoundException("User with Given Id does not exists.");
		}
		Message message = new Message();
		message.setAction(Constants.DELETE);
		UserDto userDto = new UserDto();
		userDto.setRollNumber(rollNumber);
		message.setUserDto(userDto);
		kafkaTemplate.send(TOPIC, message);
	}

}
