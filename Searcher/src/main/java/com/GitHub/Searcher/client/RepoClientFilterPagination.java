package com.GitHub.Searcher.client;

import com.GitHub.Searcher.dto.RepoDto;
import com.GitHub.Searcher.dto.UserDto;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class RepoClientFilterPagination {
StringToDateParser stringToDateParser = new StringToDateParser();
private final String sortReposByUpdatedDateDesc = "?sort=updated&direction=desc";
private final String sortReposByNameAsc = "?sort=full_name&direction=asc";
private final String sortReposByNameDesc = "?sort=full_name&direction=desc";

UserDto userDto = new UserDto();
UserRepoCounter userRepoCounter = new UserRepoCounter();
private RepoDto[] repos;
private List<RepoDto> repoDtoList;
private int numberOfUserRepos;

    public List<RepoDto> getReposByAuthorAndFilterOption(int page, String authorName, int filter) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String filterOption="null";
        List<RepoDto> reposList= new LinkedList<RepoDto>();
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
           /* if (filter ==3)
            {
                filterOption=sortReposByUpdatedDateDesc;
            } */
        }
        else filterOption="";
        ResponseBody body;
            Request request = new Request.Builder().url("https://api.github.com/users/" + authorName + "/repos?page=" + page + "&per_page=1000&" + filterOption).build();
            Call call = client.newCall(request);
            Response response = call.execute();
            body = response.body();
        assert body != null;
        String json = body.string();
        Gson gson = new Gson();
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
        return reposList;
    }


}
