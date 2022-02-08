package com.training.task.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.training.task.constants.Constants;
import com.training.task.consumerservice.UserConsumerService;
import com.training.task.entity.User;
import com.training.task.message.Message;
import com.training.task.util.UserUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(prefix="kafka",value="enabled")
public class UserConsumer {

	@Autowired
	private UserConsumerService userConsumerService;

	@KafkaListener(topics = "TrainingTask3", groupId = "TaskGroup", containerFactory = "userKafkaListenerFactory")
	public void consume(Message message) {
		log.info("Payload received");

		if (message.getAction().equals(Constants.CREATE)) {
			User user = UserUtil.convertDtoToUser(message.userDto);
			userConsumerService.createUser(user);

		}
		if (message.getAction().equals(Constants.UPDATE)) {
			User user = UserUtil.convertDtoToUser(message.userDto);
			userConsumerService.updateUser(user);
		}

		if (message.getAction().equals(Constants.DELETE)) {
			User user = UserUtil.convertDtoToUser(message.userDto);
			userConsumerService.deleteUser(user);

		}
	}

}
