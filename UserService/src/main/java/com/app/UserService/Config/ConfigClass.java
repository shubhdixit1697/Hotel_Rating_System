package com.app.UserService.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.app.UserService.Config.Interceptor.RestTemplateInterceptors;

@Configuration
public class ConfigClass {

	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;

	@Autowired
	private OAuth2AuthorizedClientRepository auth2AuthorizedClientRepo;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

		interceptors
				.add(new RestTemplateInterceptors(manager(clientRegistrationRepository, auth2AuthorizedClientRepo)));
		restTemplate.setInterceptors(null);
		return restTemplate;
	}

	@Bean
	public OAuth2AuthorizedClientManager manager(ClientRegistrationRepository ClientRegistrationRepository,
			OAuth2AuthorizedClientRepository auth2AuthorizedClientManager) {
		OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials()
				.build();

		DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
				ClientRegistrationRepository, auth2AuthorizedClientManager);

		defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);

		return defaultOAuth2AuthorizedClientManager;
	}

}
