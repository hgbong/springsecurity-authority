package com.example.springsecurity_authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableRedisHttpSession // spring.session.store-type 지정 시 생략 가능
public class SpringsecurityAuthorityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityAuthorityApplication.class, args);
	}

}
