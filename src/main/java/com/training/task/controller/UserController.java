package com.training.task.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.training.task.message.UserDto;
import com.training.task.userservice.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	protected UserService userService;

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
		userService.createUser(userDto);
		return new ResponseEntity<>("Request Successful", HttpStatus.ACCEPTED);
	}

	@GetMapping
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("{rollNumber}")
	public ResponseEntity<?> getUser(@PathVariable String rollNumber) {
		UserDto userDto = userService.getUser(rollNumber);
		return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
	}

	@PutMapping("{rollNumber}")
	public ResponseEntity<String> updateUser(@PathVariable String rollNumber, @RequestBody UserDto userDto) {
		userService.updateUser(userDto);
		return new ResponseEntity<>("Request Successfull", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("{rollNumber}")
	public ResponseEntity<String> deleteUser(@PathVariable String rollNumber) {
		userService.deleteUser(rollNumber);
		return new ResponseEntity<>("Request Successfull", HttpStatus.ACCEPTED);
	}

}
