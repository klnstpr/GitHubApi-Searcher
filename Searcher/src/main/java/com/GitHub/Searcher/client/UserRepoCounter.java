package com.GitHub.Searcher.client;

import com.GitHub.Searcher.dto.RepoDto;
import com.GitHub.Searcher.dto.UserDto;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class UserRepoCounter {
    public UserDto getNumberOfUserRepo(String authorName) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.github.com/users/" + authorName).build();
        Call call = client.newCall(request);
        Response response = call.execute();
        ResponseBody body = response.body();
        assert body != null;
        String json = body.string();
        Gson gson = new Gson();
        UserDto userDto = gson.fromJson(json, UserDto.class);
        return userDto;
    }

    public int pageCounter(int numberOfUserRepo, int repoPerPage)
    {
        double numberOfPageResult=(double)numberOfUserRepo/(double)repoPerPage;
        numberOfPageResult=Math.ceil(numberOfPageResult);
        return (int) numberOfPageResult;
    }
}
