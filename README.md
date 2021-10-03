

Build a simple web maven project by simply going to [Spring Initializr](https://start.spring.io).

- Include following dependencies to start with ribbon load balancer.
```
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
```

- Add following to the application.yml
```
# Name of load-balancer application
spring:
  application:
    name: ribbon-load-balancer

# By default the application will run on 8080 but to make sure
# the port doesn't collide with other services, change it!
server:
  port: 9090

# 'test-client' is the name of application you want to connect 
test-client:
  ribbon:
    eureka:
      enabled: false
    listOfServers: localhost:8083,localhost:8084
    ServerListRefreshInterval: 15000
```

	
	
- Write a configuration class to setup ribbon configuration as follow:
```
public class RibbonConfiguration {

	@Autowired
    IClientConfig ribbonClientConfig;
 
    @Bean
    public IPing ribbonPing(IClientConfig config) {
        return new PingUrl();
    }
 
}
```
# @RibbonClient should be added on class from where you are calling an external api
- @RibbonClient annotation is used to enable ribbon configuration while calling external api
  ```
  @RibbonClient(name="test-client",configuration = RibbonConfiguration.class)
  ```
# @LoadBalanced should be added on RestTemplate bean which is used to call an external api
- @LoadBalanced annotation is used to let RestTemplate identify external api address through its name
```
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
```