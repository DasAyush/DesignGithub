package io.codefault.githubsummer.ApiInterfaces;

import java.util.List;

import io.codefault.githubsummer.Models.CommentBody;
import io.codefault.githubsummer.Models.IssueCommentModel;
import io.codefault.githubsummer.Models.IssueModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by yashladha on 8/12/17.
 */

public interface IssueClient {
    @GET("/user/issues")
    Call<List<IssueModel>> getUserIssue(@Header("Authorization") String header);

    @GET("/repos/{owner}/{repo_name}/issues/{issue_no}/comments")
    Call<List<IssueCommentModel>> getComments(@Header("Authorization") String header,
                                              @Path("owner") String owner,
                                              @Path("repo_name") String reponame,
                                              @Path("issue_no") String issueNo);

    @POST("/repos/{owner}/{repo_name}/issues/{issue_no}/comments")
    Call<IssueCommentModel> postComment(@Header("Authorization") String header,
                                        @Path("owner") String owner,
                                        @Path("repo_name") String reponame,
                                        @Path("issue_no") String issueNo,
                                        @Body CommentBody body);
}
