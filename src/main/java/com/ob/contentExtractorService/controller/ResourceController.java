package com.ob.contentExtractorService.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/rest/contentExtractor")
public class ResourceController {

	
	@Autowired 
	RestTemplate restTemplate;
	
	/**
	 * Hystrix fallback method for extractAndUpdateContent(@PathVariable("id")
	 * @param id
	 * @return String
	 */
	public String getByIdFallBack(String id)
	{
		return "The server is currently down";
	}
	
	/**
	 * 
	 * @param id
	 * @return String
	 */
	@HystrixCommand(fallbackMethod="getByIdFallBack", 
	commandKey="db-service-checker", groupKey="mongoServiceQueryer")
	@GetMapping("/{id}")
	public String extractAndUpdateContent(@PathVariable("id") final String id)
	{
		String statusCode = null;
		ResponseEntity<String> fileObject = restTemplate.getForEntity("http://MONGODBSERVICE/rest/db/uri/{id}",String.class,id);
		if(fileObject.getStatusCode().is2xxSuccessful()){
			
			try 
			{
				String content = FileUtils.readFileToString(new File(fileObject.getBody()), "UTF-8");
				
				//not used
				//MultiValueMap<String,String> headers = new LinkedMultiValueMap<String, String>();
				
				
				//To encode the document uncomment this line of code
				//content = Base64.getEncoder().encodeToString(content.getBytes(StandardCharsets.UTF_8));
				
				//headers.add("body",content);
				
				//headers.add("body", content);
				//HttpEntity<String> requestEntity = new HttpEntity<Object>(headers); 
				
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.TEXT_PLAIN);
				HttpEntity<String> requestEntity = new HttpEntity<String>(content, headers);
				
				ResponseEntity<String> response = restTemplate.exchange("http://MONGODBSERVICE/rest/db/{id}/updateBody",HttpMethod.PUT, requestEntity, String.class,id);
				statusCode = response.getStatusCode().toString();
			} 
			catch (IOException e)
			{
			
				e.printStackTrace();
			}
		}
		return statusCode;
	}
	
	

}
