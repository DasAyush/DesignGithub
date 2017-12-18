package io.codefault.githubsummer.ApiInterfaces;

import java.util.List;

import io.codefault.githubsummer.Models.PublicRepoModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface PublicRepoClient {
    @GET("/repositories")
    Call<List<PublicRepoModel>> getPublic(@Header("Authorization") String header);
} 