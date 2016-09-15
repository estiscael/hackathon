package edu;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;

@SpringBootApplication
public class GitHubAdapterTestRunner extends GitHubAdapterApplication{

   @Bean
   public RestTemplate restTemplate(){
       return mock(RestTemplate.class);
   }

}
