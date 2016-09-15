package edu.git;

import edu.git.github.GitHubRepository;
import edu.git.github.GitHubRestClient;
import org.hamcrest.Matchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryServiceTest {

    private static final String OWNER = "OWNER";
    private static final String REPOSITORY_NAME = "REPO";
    private static final String CLONE_URL = "clone_url";
    private static final String CREATED_AT = "created_at";
    private static final String DESCRIPTION = "desc";
    private static final String FULL_NAME = "name";
    private static final int STARS = 222;
    public static final String ONE = "1";
    public static final String TWO = "2";

    @Mock private GitHubRestClient gitHubRestClient;
    private RepositoryService gitRepositoryService;

    @Before
    public void setUp(){
        gitRepositoryService = new RepositoryService(gitHubRestClient);
    }

    @Test
    public void gitRepositoryReturnedForOwnerWithRepo(){
        when(gitHubRestClient.getRepositoryOfOwnerByName(anyString(), anyString())).thenReturn(gitHubRepository(ONE));

        Repository repo = gitRepositoryService.getRepositoryOfOwnerByName(OWNER, REPOSITORY_NAME);
        assertThat(repo, equalTo(gitRepository(ONE)));
    }

    @Test
    public void gitRepositoriesReturnedForOwner(){
        when(gitHubRestClient.getRepositoriesOfOwner(anyString())).thenReturn(Arrays.asList(gitHubRepository(ONE), gitHubRepository(TWO)));

        List<Repository> gitRepositories = gitRepositoryService.getRepositoriesOfOwner(OWNER);
        assertThat(gitRepositories, Matchers.containsInAnyOrder(gitRepository(ONE), gitRepository(TWO)));
    }

    private Repository gitRepository(String suffix) {
        return Repository.theGitRepository()
                .withCloneUrl(CLONE_URL + suffix)
                .withCreatedAt(CREATED_AT + suffix)
                .withDescription(DESCRIPTION + suffix)
                .withFullName(FULL_NAME + suffix)
                .withStars(STARS)
                .build();
    }

    private GitHubRepository gitHubRepository(String suffix) {
        return GitHubRepository.theGitHubRepository()
                .withCloneUrl(CLONE_URL + suffix)
                .withCreatedAt(CREATED_AT + suffix)
                .withDescription(DESCRIPTION + suffix)
                .withFullName(FULL_NAME + suffix)
                .withStars(STARS)
                .build();
    }

}