package edu.git;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repositories/{owner}")
public class RepositoryController {

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Repository> getRepositoriesOfOwner(@PathVariable("owner")String owner){
        return repositoryService.getRepositoriesOfOwner(owner);
    }

    @RequestMapping(value = "/{repoName}", method = RequestMethod.GET)
    public Repository getRepositoryOfOwnerByName(@PathVariable("owner") String owner,
                                    @PathVariable("repoName") String repoName) {
        return repositoryService.getRepositoryOfOwnerByName(owner, repoName);
    }

}
