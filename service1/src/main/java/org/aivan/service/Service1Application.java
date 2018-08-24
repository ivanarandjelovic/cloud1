package org.aivan.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Service1Application {

	static Log log = LogFactory.getLog(Service1Application.class);

	@Value("${spring.application.name}")
	String appName;

	public static void main(String[] args) {
		log.info("Application starting ...");
		SpringApplication.run(Service1Application.class, args);
	}

	@RequestMapping("/")
	public String service1() {
		log.info("service1 method called!");
		return "Hello from " + appName + "!";
	}
}
