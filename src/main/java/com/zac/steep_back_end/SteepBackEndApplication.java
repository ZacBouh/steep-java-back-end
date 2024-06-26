package com.zac.steep_back_end;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class SteepBackEndApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SteepBackEndApplication.class, args);
		
		System.out.println("LENGTH IS 8888888888888888888888888888888888888888888888888888888888888888");

	}
}