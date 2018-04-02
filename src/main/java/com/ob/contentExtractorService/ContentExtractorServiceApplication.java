package com.ob.contentExtractorService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

//in this application, we are streaming everything to 
//the hystrix dashboard, but we can use Turbine to pipe
//all the hystrix notifications for each service
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class ContentExtractorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentExtractorServiceApplication.class, args);
	}
}
