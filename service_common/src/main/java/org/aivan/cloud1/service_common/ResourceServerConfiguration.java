package org.aivan.cloud1.service_common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
public abstract class ResourceServerConfiguration {

	// added
	@Autowired
	private ResourceServerProperties sso;

	// added
	@Bean
	public ResourceServerTokenServices myUserInfoTokenServices(
			@Autowired UserInfoRestTemplateFactory restTemplateFactory) {
		return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId(), restTemplateFactory);
	}

}
