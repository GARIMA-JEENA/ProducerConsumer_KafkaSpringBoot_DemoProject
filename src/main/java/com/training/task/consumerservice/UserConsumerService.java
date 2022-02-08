package com.training.task.consumerservice;

import org.springframework.stereotype.Service;

import com.training.task.entity.User;

@Service
public interface UserConsumerService {

	public void createUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);
}
