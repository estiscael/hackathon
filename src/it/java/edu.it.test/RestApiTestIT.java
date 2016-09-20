package edu.it.test;


import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestApiTestIT {


    @Test
    public void shouldReturnLatestFileByType() {
        when().get("http://45.55.244.80:9000/repositories/{owner}/{repository_name}", "estiscael", "hackathon")
                .then().statusCode(HttpStatus.SC_OK)
                .body("fullName", equalTo("estiscael/hackathon"))
                .body("description", equalTo(null))
                .body("cloneUrl", equalTo("https://github.com/estiscael/hackathon.git"))
                .body("stars", equalTo(0))
                .body("createdAt", equalTo("2016-09-15T15:50:21Z"));
    }
}
