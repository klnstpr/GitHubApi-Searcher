package com.GitHub.Searcher.client;

import com.GitHub.Searcher.dto.RepoDto;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RepoClientWithoutPagionation {
    StringToDateParser stringToDateParser = new StringToDateParser();
    UserRepoCounter userRepoCounter = new UserRepoCounter();

    public List<RepoDto> getReposByAuthor(String authorName) throws IOException {
        int repoPerPage = 100;
        int numberOfUserRepos=userRepoCounter.getNumberOfUserRepo(authorName).getPublic_repos();
        int numberOfPage=userRepoCounter.pageCounter(numberOfUserRepos, repoPerPage);
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        List<RepoDto> reposList= new LinkedList<RepoDto>();
        for(int i=1; i<=numberOfPage; i++) {
            Request request = new Request.Builder().url("https://api.github.com/users/" + authorName + "/repos?page=" + i + "&per_page=" + repoPerPage + "&" ).build();
            Call call = client.newCall(request);
            Response response = call.execute();
            ResponseBody body = response.body();
            assert body != null;
            String json = body.string();
            RepoDto[] repos = gson.fromJson(json, RepoDto[].class);
            int k = repos.length;
            for (int j = 0; j < k; j++) {
                repos[j].setUpdatedWithinThreeMonths(stringToDateParser.isUpdatedWithin3Months(repos[j].getUpdated_at()));
                reposList.add(repos[j]);
            }
        }
        return reposList;
    }
}
