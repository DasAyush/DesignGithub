package io.codefault.githubsummer.ApiInterfaces;

import java.util.List;

import io.codefault.githubsummer.Models.IssueModel;
import io.codefault.githubsummer.Models.PullRequestEventModel;
import io.codefault.githubsummer.Models.PullRequestMergeModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by dell pc on 10-12-2017.
 */

public interface PullRequestClient {

    //@GET("/user/pulls")
    //Call<List<PullRequestEventModel>> getUserPullRequest(@Header("Authorization") String header);

    @GET("/repos/{owner}/{repo}/pulls")
    Call<List<PullRequestEventModel>> getUserPullRequest(@Header("Authorization") String header,
                                                         @Path("owner") String owner,
                                                         @Path("repo") String repo);

    @PUT("/repos/{owner}/{repo}/pulls/{number}/merge")
    Call<PullRequestMergeModel> mergePullRequest(@Header("Authorization") String header,
                                                 @Path("owner") String owner,
                                                 @Path("repo") String repo,
                                                 @Path("number") String number);
}
