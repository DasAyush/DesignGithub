package io.codefault.githubsummer.ApiInterfaces;

import java.util.List;

import io.codefault.githubsummer.Models.IssueEventModel;
import io.codefault.githubsummer.Models.IssueModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by yashladha on 8/12/17.
 */

public interface IssueClient {
    @GET("/user/issues")
    Call<List<IssueModel>> getUserIssue(@Header("Authorization") String header);
}
