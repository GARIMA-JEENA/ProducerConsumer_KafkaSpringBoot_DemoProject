package com.training.task.userservice;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.training.task.message.UserDto;

public interface UserService {

	public List<UserDto> getAllUsers();

	public UserDto getUser(String rollNumber);

	public void createUser(UserDto userDto);

	public void updateUser(UserDto userDto);

	public void deleteUser(String rollNumber);

	public List<UserDto> getAllUsers(Pageable page);

}
