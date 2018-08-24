package org.aivan.service;

import java.net.URI;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class ServiceAApplication {

	private static final String SERVICE1_NAME = "service1";

	static Log log = LogFactory.getLog(ServiceAApplication.class);

	@Value("${spring.application.name}")
	String appName;

	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public static void main(String[] args) {
		log.info("Application starting ...");
		SpringApplication.run(ServiceAApplication.class, args);
	}

	@RequestMapping("/")
	public String serviceA() {
		log.info("serviceA method called!");
		
		List<ServiceInstance> services = discoveryClient.getInstances(SERVICE1_NAME);
		if(services.size() == 0) {
			log.error("Service1 is not available1");
			return "ERROR: No services1 available!";
		}
		
		ServiceInstance service1 = services.get(0);
		URI service1Url = service1.getUri();
		log.debug("service1 url is: "+service1Url.toString());
		
		RestTemplate restTemplate = restTemplateBuilder.build();
		String response = restTemplate.getForObject(service1Url, String.class);
		
		return "Hello from " + appName + "! I called other service and got: "+response;
	}
}
