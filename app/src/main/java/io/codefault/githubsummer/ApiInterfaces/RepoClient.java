package io.codefault.githubsummer.ApiInterfaces;

import java.util.List;

import io.codefault.githubsummer.Models.RepoModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RepoClient {
    @GET("/user/repos")
    Call<List<RepoModel>> getRepo(@Header("Authorization") String header);
}