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
public class ServiceAApplication {

	static Log log = LogFactory.getLog(ServiceAApplication.class);

	@Value("${spring.application.name}")
	String appName;

	public static void main(String[] args) {
		log.info("Application starting ...");
		SpringApplication.run(ServiceAApplication.class, args);
	}

	@RequestMapping("/")
	public String serviceA() {
		log.info("serviceA method called!");
		return "Hello from " + appName + "!";
	}
}
