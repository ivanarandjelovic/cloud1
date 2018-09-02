package org.aivan.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class RemoteResourceConfiguration {

//	@Bean
//	public OAuth2RestTemplate restTemplate(OAuth2ProtectedResourceDetails resource,
//			OAuth2ClientContext oauth2ClientContext) {
//		return new OAuth2RestTemplate(resource, oauth2ClientContext);
//	}

}
