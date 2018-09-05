package org.aivan.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.aivan.service.util.BearerRestTemaplateInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableResourceServer
@EnableOAuth2Sso
@EnableOAuth2Client
public class ServiceAApplication {

//	private static final String SERVICE1_NAME = "service1";

	static Log log = LogFactory.getLog(ServiceAApplication.class);

	@Value("${spring.application.name}")
	String appName;

	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	RestTemplate restTemplate;

	// added
	@Autowired
	private ResourceServerProperties sso;

	// added
	@Bean
	@Primary
	public ResourceServerTokenServices myUserInfoTokenServices(
			@Autowired UserInfoRestTemplateFactory restTemplateFactory) {
		return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId(), restTemplateFactory);
	}

	/*
	 * @Autowired private OAuth2RestTemplate oauth2RestTemplate;
	 */
	@Autowired
	ServiceAConfiguration configuration;

	public static void main(String[] args) {
		log.info("Application starting ...");
		SpringApplication.run(ServiceAApplication.class, args);
	}

	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	@PostConstruct
	public void buildRestTemplate() {
		restTemplate = restTemplateBuilder.build();
	}
	
	@PostConstruct
	public void addInterceptors() {
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		interceptors.add(new BearerRestTemaplateInterceptor(oauth2ClientContext));
		restTemplate.setInterceptors(interceptors);
	}

	@RequestMapping("/")
	public String serviceA() {
		log.info("serviceA method called! Configuration prop 2 : " + configuration.getTestProperty());

//		List<ServiceInstance> services = discoveryClient.getInstances(SERVICE1_NAME);
//		if(services.size() == 0) {
//			log.error("Service1 is not available1");
//			return "ERROR: No services1 available!";
//		} else {
//			log.info("There are "+services.size()+" services");
//			for(ServiceInstance si:services) {
//				log.info("instance = "+si.toString());
//			}
//		}
//		
//		ServiceInstance service1 = services.get(0);
//		URI service1Url = service1.getUri();
//		log.debug("service1 url is: "+service1Url.toString());

		

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + oauth2ClientContext.getAccessToken());
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		log.warn("using service1 URL: " + configuration.getService1Url());
		ResponseEntity<String> response = restTemplate.exchange(configuration.getService1Url(), HttpMethod.GET, entity,
				String.class);

		return "Hello 2 from " + appName + "! I called other service and got: " + response.getBody();
	}
}
