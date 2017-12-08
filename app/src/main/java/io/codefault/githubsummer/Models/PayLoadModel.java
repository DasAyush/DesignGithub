package io.codefault.githubsummer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yashladha on 7/12/17.
 */

public class PayLoadModel {

    @SerializedName("comment")
    @Expose
    private CommentModel comment;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("pull_request")
    @Expose
    private PullRequestModel pullRequestModel;

    @SerializedName("action")
    @Expose
    private String action;

    @SerializedName("issue")
    @Expose
    private IssueModel issueModel;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public IssueModel getIssueModel() {
        return issueModel;
    }

    public void setIssueModel(IssueModel issueModel) {
        this.issueModel = issueModel;
    }

    public PullRequestModel getPullRequestModel() {
        return pullRequestModel;
    }

    public void setPullRequestModel(PullRequestModel pullRequestModel) {
        this.pullRequestModel = pullRequestModel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CommentModel getComment() {
        return comment;
    }

    public void setComment(CommentModel comment) {
        this.comment = comment;
    }
}
