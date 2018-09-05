package org.aivan.service.util;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

/**
 * take Bearer token used when we are called and use the same one for calling nested services
 * @author aivan
 *
 */
public class BearerRestTemaplateInterceptor implements ClientHttpRequestInterceptor {

	OAuth2ClientContext oauth2ClientContext;

	public BearerRestTemaplateInterceptor(OAuth2ClientContext oauth2ClientContext) {
		super();
		this.oauth2ClientContext = oauth2ClientContext;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		request.getHeaders().add("Authorization", "Bearer " + oauth2ClientContext.getAccessToken().getValue());

		return execution.execute(request, body);

	}

}
