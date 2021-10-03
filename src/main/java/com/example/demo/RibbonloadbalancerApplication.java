package com.example.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RibbonClient(name="test-client",configuration = RibbonConfiguration.class)
public class RibbonloadbalancerApplication {

	@Autowired
	RestTemplate restTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(RibbonloadbalancerApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@GetMapping("/invoke")
	public String invoke() throws IOException, InterruptedException {
		return restTemplate.getForObject("http://test-client/checkport",String.class);
	}

}
