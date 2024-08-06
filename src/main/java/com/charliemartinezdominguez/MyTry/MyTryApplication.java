package com.charliemartinezdominguez.MyTry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.charliemartinezdominguez.MyTry.user.UserRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class MyTryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTryApplication.class, args);
	}

}
