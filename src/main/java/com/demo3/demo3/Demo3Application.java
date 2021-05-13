package com.demo3.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Demo3Application {

	public static void main(String[] args) {

		SpringApplication.run(Demo3Application.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {

		return new RestTemplate();
	}

}
