package org.aivan.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("service-a")
public class ServiceAConfiguration {

	String service1Url;

	// @Value("${service-a.testProperty}")
	String testProperty;

	public String getService1Url() {
		return service1Url;
	}

	public void setService1Url(String service1Url) {
		this.service1Url = service1Url;
	}

	public String getTestProperty() {
		return testProperty;
	}

	public void setTestProperty(String testProperty) {
		this.testProperty = testProperty;
	}

}
