
package com.GitHub.Searcher.RestApi;

import com.GitHub.Searcher.client.*;
import com.GitHub.Searcher.dto.RepoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.List;

@RestController
public class GitApi {
    RepoClientFilterPagination repoClientFilterPagination = new RepoClientFilterPagination();
    RepoClientPagination repoClientPagination =new RepoClientPagination();
    RepoClientFilterWithoutPagination repoClientFilterWithoutPagination = new RepoClientFilterWithoutPagination();
    RepoClientWithoutPagionation repoClientWithoutPagination =new RepoClientWithoutPagionation();


   @GetMapping("page{page}/{authorName}")
    public List<RepoDto> findGitUserRepoWithPagination(@PathVariable int page, @PathVariable String authorName) throws IOException {
        //userRepoCounter.getNumberOfUserRepo(authorName);
        return repoClientPagination.getReposByPage(page, authorName);
    }


/*filter options
 1 - sortReposByNameAsc = "?sort=full_name&direction=asc";
 2 - sortReposByNameDesc = "?sort=full_name&direction=desc";
 3 - get repos updated within 3 months;
 */
    @GetMapping("page{page}/{authorName}/{filterOption}")
    public List<RepoDto> findGitUserRepoSortFilterAndPagination(@PathVariable int page, @PathVariable String authorName, @PathVariable int filterOption) throws IOException {
        //repoClient.getReposByAuthor(authorName);
        return repoClientFilterPagination.getReposByAuthorAndFilterOption(page, authorName, filterOption);
    }

    @GetMapping("/{authorName}")
    public List<RepoDto> findGitUserRepoWithoutPagination(@PathVariable String authorName) throws IOException {
        //userRepoCounter.getNumberOfUserRepo(authorName);
        return repoClientWithoutPagination.getReposByAuthor(authorName);
    }

    /*filter options
 1 - sortReposByNameAsc = "?sort=full_name&direction=asc";
 2 - sortReposByNameDesc = "?sort=full_name&direction=desc";
 3 - get repos updated within 3 months;
 */
    @GetMapping("{authorName}/{filterOption}")
    public List<RepoDto> findGitUserRepoSortFilterWithoutPagination(@PathVariable String authorName, @PathVariable int filterOption) throws IOException {
        //repoClient.getReposByAuthor(authorName);
        return repoClientFilterWithoutPagination.getReposByAuthorAndFilter(authorName, filterOption);
    }
}

