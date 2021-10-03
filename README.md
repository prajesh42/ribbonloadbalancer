# ribbonloadbalancer

Build a simple web maven project by simply going to https://start.spring.io.
Include following dependencies to start with ribbon load balancer.

<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
<version>2.2.9.RELEASE</version>
</dependency>

<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
</dependencyManagement>

Add following to the application.yml (rename application.properties to application.yml)

spring:
  application:
    name: ribbon-load-balancer
 
server:
  port: 9090 
 
test-client:
  ribbon:
    eureka:
      enabled: false
    listOfServers: localhost:8083,localhost:8084
    ServerListRefreshInterval: 15000
	
	
Write a configuration class to setup ribbon configuration as follow:

public class RibbonConfiguration {

	@Autowired
    IClientConfig ribbonClientConfig;
 
    @Bean
    public IPing ribbonPing(IClientConfig config) {
        return new PingUrl();
    }
 
}

- use @RibbonClient annotation to enable ribbon configuration while calling external api
- use @LoadBalanced annotation to let RestTemplate identify external api address through its name