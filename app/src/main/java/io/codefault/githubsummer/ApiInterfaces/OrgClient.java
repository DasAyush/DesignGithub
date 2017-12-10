package io.codefault.githubsummer.ApiInterfaces;

import java.util.List;

import io.codefault.githubsummer.Models.OrgModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface OrgClient {
    @GET("/user/orgs")
    Call<List<OrgModel>> getOrg(@Header("Authorization") String header);
}