package com.training.task.consumerserviceimplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.task.consumerservice.UserConsumerService;
import com.training.task.dao.UserDao;
import com.training.task.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserConsumerServiceImplement implements UserConsumerService {

	@Autowired
	private UserDao userDao;

	@Override
	public void createUser(User user) {
		userDao.save(user);
		log.info("Created " + user);
	}

	@Override
	public void updateUser(User user) {
		userDao.save(user);
		log.info("Details updated " + user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.deleteById(user.getRollNumber());
		log.info("Deleted " + user);
	}

}
