package edu.git;

import edu.git.github.GitHubRepository;
import edu.git.github.GitHubRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static edu.git.Repository.theGitRepository;

@Service
public class RepositoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryService.class);

    private GitHubRestClient gitHubRestClient;

    @Autowired
    public RepositoryService(GitHubRestClient gitHubRestClient){
        this.gitHubRestClient = gitHubRestClient;
    }

    public List<Repository> getRepositoriesOfOwner(String owner) {
        List<Repository> repositories = new ArrayList<>();
        List<GitHubRepository> gitHubRepositories = gitHubRestClient.getRepositoriesOfOwner(owner);
        gitHubRepositories.forEach(repo -> repositories.add(createRepository(repo)));

        return repositories;
    }

    public Repository getRepositoryOfOwnerByName(String owner, String repoName) {
        LOGGER.info("Taking repository details from GitHub for owner: {}, repository: {}", owner, repoName);
        GitHubRepository repo = gitHubRestClient.getRepositoryOfOwnerByName(owner, repoName);
        return createRepository(repo);
    }

    private Repository createRepository(GitHubRepository gitHubRepository) {
        return theGitRepository()
                .withFullName(gitHubRepository.getFullName())
                .withDescription(gitHubRepository.getDescription())
                .withCreatedAt(gitHubRepository.getCreatedAt())
                .withStars(gitHubRepository.getStars())
                .withCloneUrl(gitHubRepository.getCloneUrl())
                .build();
    }

}
