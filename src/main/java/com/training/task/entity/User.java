package com.training.task.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document
@Data
@Builder
public class User {

	@Id
	private String rollNumber;
	private String userName;
	private String stream;
	private String location;

}
