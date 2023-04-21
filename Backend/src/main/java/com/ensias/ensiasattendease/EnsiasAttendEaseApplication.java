package com.ensias.ensiasattendease;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ensias.ensiasattendease.models.UserModel;

@SpringBootApplication
public class EnsiasAttendEaseApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EnsiasAttendEaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
	}
	

}
