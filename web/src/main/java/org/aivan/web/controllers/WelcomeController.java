package org.aivan.web.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class WelcomeController {

	@Value("${web.serviceAUrl}")
	String serviceAUrl;
	
	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		String response = restTemplate.getForObject(serviceAUrl, String.class);
		
		model.put("message", response);
		return "welcome";
	}

}