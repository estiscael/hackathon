package edu.git;

import java.util.Objects;

public class Repository {

    private String fullName;
    private String description;
    private String cloneUrl;
    private int stars;
    private String createdAt;

    public String getFullName() {
        return fullName;
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

    public int getStars() {
        return stars;
    }

    public static RepositoryBuilder theGitRepository(){
        return new RepositoryBuilder();
    }

    public static class RepositoryBuilder{
        private final Repository repository;

        RepositoryBuilder(){
            repository = new Repository();
        }

        public RepositoryBuilder withFullName(String fullName){
            repository.fullName = fullName;
            return this;
        }

        public RepositoryBuilder withDescription(String description){
            repository.description = description;
            return this;
        }

        public RepositoryBuilder withCloneUrl(String cloneUrl) {
            repository.cloneUrl = cloneUrl;
            return this;
        }

        public RepositoryBuilder withCreatedAt(String createdAt){
            repository.createdAt = createdAt;
            return this;
        }

        public RepositoryBuilder withStars(int starts) {
            repository.stars = starts;
            return this;
        }

       public Repository build(){
           return repository;
       }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Repository other = (Repository) o;
        return Objects.equals(this.fullName, other.fullName)
                && Objects.equals(this.cloneUrl, other.cloneUrl)
                && Objects.equals(this.createdAt, other.createdAt)
                && Objects.equals(this.description, other.description)
                && Objects.equals(this.stars, other.stars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, cloneUrl, createdAt, description, stars);
    }

}
