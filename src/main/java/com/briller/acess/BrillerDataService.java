package com.briller.acess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;



@SpringBootApplication
@EnableAsync
@ComponentScan({ "com.briller.acess" })
public class BrillerDataService {
	private static final Logger log = LoggerFactory.getLogger(BrillerDataService.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		log.info("Application started.....");

		SpringApplication.run(BrillerDataService.class, args);

	}

}
