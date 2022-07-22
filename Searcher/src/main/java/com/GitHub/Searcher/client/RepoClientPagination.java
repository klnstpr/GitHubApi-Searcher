package com.GitHub.Searcher.client;

import com.GitHub.Searcher.dto.RepoDto;
import com.GitHub.Searcher.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class RepoClientPagination {
    ObjectMapper mapper = new ObjectMapper();

    StringToDateParser stringToDateParser = new StringToDateParser();
    UserDto userDto = new UserDto();
    UserRepoCounter userRepoCounter = new UserRepoCounter();
    private List<RepoDto> repoDtoList;
    private int numberOfUserRepos;

    public List<RepoDto> getReposByPage(int page, String authorName) throws IOException {
        int repoPerPage = 100;
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.github.com/users/" + authorName + "/repos?page=" + page + "&per_page=" + repoPerPage + "&").build();
        Call call = client.newCall(request);
        Response response = call.execute();
        ResponseBody body = response.body();
        assert body != null;
        String json = body.string();
        RepoDto[] repos = gson.fromJson(json, RepoDto[].class);
        int k = repos.length;
        for (int j = 0; j < k; j++) {
            repos[j].setUpdatedWithinThreeMonths(stringToDateParser.isUpdatedWithin3Months(repos[j].getUpdated_at()));
        }
        return List.of(repos);
    }

}
