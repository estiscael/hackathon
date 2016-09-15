package edu.git.github;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubRepository {
    private String fullName;
    private String cloneUrl;
    private String createdAt;
    private String description;
    private int stars;

    @JsonCreator
    public GitHubRepository(@JsonProperty("full_name") String fullName,
                            @JsonProperty("clone_url") String cloneUrl,
                            @JsonProperty("created_at") String createdAt,
                            @JsonProperty("description") String description,
                            @JsonProperty("stargazers_count") int stars){
        this.fullName = fullName;
        this.cloneUrl = cloneUrl;
        this.createdAt = createdAt;
        this.description = description;
        this.stars = stars;

    }

    public String getFullName() {
        return fullName;
    }

    public int getStars() {
        return stars;
    }

    public String getDescription() {
        return description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public static RepositoryBuilder theGitHubRepository(){
        return new RepositoryBuilder();
    }

    public static class RepositoryBuilder{
        private String fullName;
        private String cloneUrl;
        private String createdAt;
        private String description;
        private int stars;

        public RepositoryBuilder withFullName(String fullName){
            this.fullName = fullName;
            return this;
        }

        public RepositoryBuilder withDescription(String description){
            this.description = description;
            return this;
        }

        public RepositoryBuilder withCloneUrl(String cloneUrl) {
            this.cloneUrl = cloneUrl;
            return this;
        }

        public RepositoryBuilder withCreatedAt(String createdAt){
            this.createdAt = createdAt;
            return this;
        }

        public RepositoryBuilder withStars(int starts) {
            this.stars = starts;
            return this;
        }

        public GitHubRepository build(){
            return new GitHubRepository(fullName, cloneUrl, createdAt, description, stars);
        }
    }

}
