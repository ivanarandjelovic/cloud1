package org.aivan.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("service-a")
public class ServiceAConfiguration {

	String service1Url;
	
	String testPropery;

	public String getService1Url() {
		return service1Url;
	}

	public void setService1Url(String service1Url) {
		this.service1Url = service1Url;
	}

	public String getTestPropery() {
		return testPropery;
	}

	public void setTestPropery(String testPropery) {
		this.testPropery = testPropery;
	}
	
	

}
