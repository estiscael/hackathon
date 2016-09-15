package edu.git;

import com.jayway.restassured.RestAssured;
import edu.GitHubAdapterTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.jayway.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.springframework.http.HttpStatus.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GitHubAdapterTestRunner.class)
@WebAppConfiguration
@IntegrationTest("server.port:10000")
public class ExceptionHandlerControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp(){
        RestAssured.port = port;
    }

    @Test
    public void restClientExceptionAreCaughtAndStatusCode404IsReturnedInResponseWithDetailsAndDescription() {
        Mockito.when(restTemplate.getForObject(anyString(), any(Class.class)))
                .thenThrow(new HttpClientErrorException(BAD_REQUEST));

        when().get("/repositories/{owner}/{repository_name}", "owner", "repoName")
                .then().statusCode(SC_NOT_FOUND)
                .body("description", equalTo("Current user repository was not found"))
                .body("message", equalTo("400 BAD_REQUEST"));
    }

    @Test
    public void servletExceptionAreCaughtAndStatusCode404IsReturnedInResponseWithDetailsAndDescription() {
        when().get("/repositories1")
                .then().statusCode(SC_NOT_FOUND)
                .body("description", equalTo("Incorrect request url"))
                .body("message", equalTo("No handler found for GET /repositories1"));
    }

}