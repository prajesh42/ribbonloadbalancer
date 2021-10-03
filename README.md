

#Build a simple web maven project by simply going to [Spring Initializr]https://start.spring.io.

-Include following dependencies to start with ribbon load balancer.

![Ribbon Dependencies](/img/dependencies.jpg).

-Add following to the application.yml (rename application.properties to application.yml)

![Ribbon Dependencies](/img/application-yml.jpg).
	
	
-Write a configuration class to setup ribbon configuration as follow:
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

- use @RibbonClient annotation to enable ribbon configuration while calling external api
- use @LoadBalanced annotation to let RestTemplate identify external api address through its name