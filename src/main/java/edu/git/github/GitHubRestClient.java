package edu.git.github;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class GitHubRestClient {

    private static final String GIT_HUB_URI = "https://api.github.com";
    private static final String GET_OWNER_REPOSITORY_BY_NAME_URI_TEMPLATE = GIT_HUB_URI + "/repos/%s/%s";
    private static final String GET_REPOSITORIES_OF_OWNER_URI_TEMPLATE =  GIT_HUB_URI + "/users/%s/repos";

    @Autowired
    private RestTemplate restTemplate;

    public GitHubRepository getRepositoryOfOwnerByName(String owner, String repoName) {
        String uri = String.format(GET_OWNER_REPOSITORY_BY_NAME_URI_TEMPLATE, owner, repoName);
        return restTemplate.getForObject(uri, GitHubRepository.class);
    }

    public List<GitHubRepository> getRepositoriesOfOwner(String owner) {
        String uri = String.format(GET_REPOSITORIES_OF_OWNER_URI_TEMPLATE, owner);
        ResponseEntity<List<GitHubRepository>> repositoriesEntity =
                restTemplate.exchange(uri, HttpMethod.GET, null,
                        new ParameterizedTypeReference <List <GitHubRepository>> (){});

        return repositoriesEntity !=null ? repositoriesEntity.getBody() : new ArrayList<>();
    }
}
