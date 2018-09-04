package org.aivan.service;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
public class Service1Application {

	static Log log = LogFactory.getLog(Service1Application.class);

	@Value("${spring.application.name}")
	String appName;

	// added
	@Autowired
	private ResourceServerProperties sso;

	// added
	@Bean
	public ResourceServerTokenServices myUserInfoTokenServices(
			@Autowired UserInfoRestTemplateFactory restTemplateFactory) {
		return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId(), restTemplateFactory);
	}

	public static void main(String[] args) {
		log.info("Application starting ...");
		SpringApplication.run(Service1Application.class, args);
	}

	@RequestMapping("/")
	@PreAuthorize("#oauth2.hasScope('test_scope') and hasAuthority('USER')")
	public String service1() {
		log.info("service1 method called 2!");
		return "Hello from " + appName + "! It's exactly " + new Date();
	}
}
