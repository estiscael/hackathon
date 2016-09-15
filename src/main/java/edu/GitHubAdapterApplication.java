package edu;

import edu.git.github.GitHubRestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GitHubAdapterApplication {

   @Bean
   public RestTemplate restTemplate(){
       return new RestTemplate();
   }

    @Bean
    public GitHubRestClient gitHubRestClient(){
        return new GitHubRestClient();
    }

    public static void main(String[] args) {
        SpringApplication.run(GitHubAdapterApplication.class, args);
    }

}
