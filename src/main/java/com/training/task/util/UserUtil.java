package com.training.task.util;

import java.util.List;
import java.util.stream.Collectors;

import com.training.task.entity.User;
import com.training.task.message.UserDto;

public class UserUtil {

	public static UserDto convertUserToDto(User user) {
		return UserDto.builder().rollNumber(user.getRollNumber()).userName(user.getUserName()).stream(user.getStream())
				.location(user.getLocation()).build();
	}

	public static List<UserDto> convertUserToDto(List<User> user) {
		return user.stream().map(x -> convertUserToDto(x)).collect(Collectors.toList());
	}

	public static User convertDtoToUser(UserDto userDto) {
		return User.builder().rollNumber(userDto.getRollNumber()).userName(userDto.getUserName())
				.stream(userDto.getStream()).location(userDto.getLocation()).build();
	}
}
