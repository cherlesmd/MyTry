package com.charliemartinezdominguez.MyTry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.charliemartinezdominguez.MyTry")
public class MyTryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTryApplication.class, args);
	}

}
