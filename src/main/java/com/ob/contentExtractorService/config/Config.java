package com.ob.contentExtractorService.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * This creates a Config for a set of objects that
 * we can autowire
 * @author oonyimadu
 *
 */
@Configuration
public class Config {
		
	@LoadBalanced //performs client side load balancing
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	
}
