package com.training.task.userservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.training.task.message.UserDto;

public interface UserService {

	public UserDto getUser(String rollNumber);

	public void createUser(UserDto userDto);

	public void updateUser(UserDto userDto);

	public void deleteUser(String rollNumber);

	public Page<UserDto> getUsers(Pageable page);

}
