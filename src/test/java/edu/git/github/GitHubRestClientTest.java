package edu.git.github;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration
public class GitHubRestClientTest {

    @Mock private RestTemplate restTemplate;
    @InjectMocks private GitHubRestClient gitHubRestClient;

    @Test
    public void gitHubRepositoryRequestedForOwnerWithRepository(){
        final String owner = "owner";
        final String repoName = "repo";

        gitHubRestClient.getRepositoryOfOwnerByName(owner, repoName);

        ArgumentCaptor<String> uri = ArgumentCaptor.forClass(String.class);
        verify(restTemplate).getForObject(uri.capture(), eq(GitHubRepository.class));
        assertThat(uri.getValue(), equalTo("https://api.github.com/repos/owner/repo"));
    }

    @Test
    public void gitHubRepositoriesOfOwnerRequested(){
        final String owner = "owner";

        gitHubRestClient.getRepositoriesOfOwner(owner);

        ArgumentCaptor<String> uri = ArgumentCaptor.forClass(String.class);
        verify(restTemplate).exchange(uri.capture(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class));
        assertThat(uri.getValue(), equalTo("https://api.github.com/users/owner/repos"));
    }

}