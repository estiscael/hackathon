package edu.git;

import com.jayway.restassured.RestAssured;
import edu.GitHubAdapterTestRunner;
import edu.git.github.GitHubRepository;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static com.jayway.restassured.RestAssured.when;
import static edu.git.github.GitHubRepository.theGitHubRepository;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.springframework.http.HttpStatus.OK;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GitHubAdapterTestRunner.class)
@WebAppConfiguration
@IntegrationTest("server.port:10001")
public class RepositoryControllerTest {

    private static final String OWNER = "mojombo1";
    private static final String REPOSITORY_NAME = "asteroids";
    private static final String FULL_NAME = "mojombo1/asteroids";
    private static final String DESCRIPTION = "Destroy your Atom editor, Asteroids style!";
    private static final String CLONE_URL = "https://github.com/mojombo/asteroids.git";
    private static final String CREATED_AT = "2014-03-03T07:40:00Z";
    private static final int STARS = 86;

    @Autowired private RestTemplate restTemplate;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp(){
        RestAssured.port = port;
    }

    @Test
    public void jsonWithRepositoryDetailsReturnedForRequestedGitHubRepositoryOfOwnerByRepoName() {
        Mockito.when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(gitHubRepository());

        when().get("/repositories/{owner}/{repository_name}", OWNER, REPOSITORY_NAME)
                .then().statusCode(HttpStatus.SC_OK)
                .body("fullName", equalTo(FULL_NAME))
                .body("description", equalTo(DESCRIPTION))
                .body("cloneUrl", equalTo(CLONE_URL))
                .body("stars", equalTo(STARS))
                .body("createdAt", equalTo(CREATED_AT));
    }

    @Test
    public void jsonWithRepositoriesReturnedForRequestedOwner() {
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(new ResponseEntity(asList(gitHubRepository()), OK));

        when().get("/repositories/{owner}", OWNER)
                .then().statusCode(HttpStatus.SC_OK)
                .body("get(0).fullName", equalTo(FULL_NAME))
                .body("get(0).description", equalTo(DESCRIPTION))
                .body("get(0).cloneUrl", equalTo(CLONE_URL))
                .body("get(0).stars", equalTo(STARS))
                .body("get(0).createdAt", equalTo(CREATED_AT));
    }

    private GitHubRepository gitHubRepository() {
        return theGitHubRepository()
                .withCloneUrl(CLONE_URL)
                .withCreatedAt(CREATED_AT)
                .withDescription(DESCRIPTION)
                .withStars(STARS)
                .withFullName(FULL_NAME)
                .build();
    }

}