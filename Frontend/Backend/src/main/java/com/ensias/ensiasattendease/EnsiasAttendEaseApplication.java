package com.ensias.ensiasattendease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EnsiasAttendEaseApplication{

	public static void main(String[] args) {
		SpringApplication.run(EnsiasAttendEaseApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfiguraion(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
						.addMapping("/**")
						.allowedHeaders("*")
						.allowedOrigins("http://localhost:4200", "http://localhost:8080") //Origins allowed , let's make all origins for now but real origin to be allowed is http://localhost:4200
						.allowedMethods("*")
						.allowCredentials(true);
			}
		};
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("Hello World");
//	}
	

}
