package com.up.usersauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLOutput;

@SpringBootApplication
public class UsersAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersAuthApplication.class, args);
		//System.out.println("Password: " + new BCryptPasswordEncoder().encode("ngaravito"));
		//String key = "49603b8577dc0660246569317e011dcd72c4e585d4bd73d842410677be728f86";
	}

}
