package com.GitHub.Searcher.client;


import com.GitHub.Searcher.dto.RepoDto;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RepoClientFilterWithoutPagination {
    StringToDateParser stringToDateParser = new StringToDateParser();
    UserRepoCounter userRepoCounter = new UserRepoCounter();

    private final String sortReposByUpdatedDateDesc = "?sort=updated&direction=desc";
    private final String sortReposByNameAsc = "?sort=full_name&direction=asc";
    private final String sortReposByNameDesc = "?sort=full_name&direction=desc";


    public List<RepoDto> getReposByAuthorAndFilter(String authorName, int filter) throws IOException {

        int repoPerPage = 100;
        int numberOfUserRepos=userRepoCounter.getNumberOfUserRepo(authorName).getPublic_repos();
        int numberOfPage=userRepoCounter.pageCounter(numberOfUserRepos, repoPerPage);
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        List<RepoDto> reposList= new LinkedList<RepoDto>();
        String filterOption="null";
        if(filter<=2)
        {
            if (filter == 1)
            {
                filterOption=sortReposByNameAsc;
            }
            if (filter == 2)
            {
                filterOption=sortReposByNameDesc;
            }
            /*if (filter ==3)
            {
                filterOption=sortReposByUpdatedDateDesc;
            }*/
        }
        else filterOption="";
        for(int i=1; i<=numberOfPage; i++) {
            Request request = new Request.Builder().url("https://api.github.com/users/" + authorName + "/repos?page=" + i + "&per_page=" + repoPerPage + "&" + filterOption).build();
            Call call = client.newCall(request);
            Response response = call.execute();
            ResponseBody body = response.body();
            assert body != null;
            String json = body.string();
            RepoDto[] repos = gson.fromJson(json, RepoDto[].class);
            int k = repos.length;
            if (filter == 1 || filter == 2) {
                for (int j = 0; j < k; j++) {
                    repos[j].setUpdatedWithinThreeMonths(stringToDateParser.isUpdatedWithin3Months(repos[j].getUpdated_at()));
                    reposList.add(repos[j]);
                }
            }
            else {
                for (int j = 0; j < k; j++) {
                    repos[j].setUpdatedWithinThreeMonths(stringToDateParser.isUpdatedWithin3Months(repos[j].getUpdated_at()));
                    if(repos[j].isUpdatedWithinThreeMonths()==true) {
                        reposList.add(repos[j]);
                    }
                }
            }
        }
        return reposList;
    }
}
