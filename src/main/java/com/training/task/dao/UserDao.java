package com.training.task.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.training.task.entity.User;

public interface UserDao extends MongoRepository<User, String> {

}
