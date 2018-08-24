package org.aivan.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class ServiceAApplication {

	static Log log = LogFactory.getLog(ServiceAApplication.class);

	@Value("${spring.application.name}")
	String appName;

	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	@Autowired
	ServiceAConfiguration configuration;
	
	public static void main(String[] args) {
		log.info("Application starting ...");
		SpringApplication.run(ServiceAApplication.class, args);
	}

	@RequestMapping("/")
	public String serviceA() {
		log.info("serviceA method called!");
		
		RestTemplate restTemplate = restTemplateBuilder.build();
		String response = restTemplate.getForObject(configuration.getService1Url(), String.class);
		
		return "Hello from " + appName + "! I called other service and got: "+response;
	}
}
