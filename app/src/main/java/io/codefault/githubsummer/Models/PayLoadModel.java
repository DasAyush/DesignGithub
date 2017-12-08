package io.codefault.githubsummer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yashladha on 7/12/17.
 */

public class PayLoadModel {

    @SerializedName("comment")
    @Expose
    private CommentEventModel comment;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("pull_request")
    @Expose
    private PullRequestEventModel pullRequestEventModel;

    @SerializedName("action")
    @Expose
    private String action;

    @SerializedName("issue")
    @Expose
    private IssueEventModel issueEventModel;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public IssueEventModel getIssueEventModel() {
        return issueEventModel;
    }

    public void setIssueEventModel(IssueEventModel issueEventModel) {
        this.issueEventModel = issueEventModel;
    }

    public PullRequestEventModel getPullRequestEventModel() {
        return pullRequestEventModel;
    }

    public void setPullRequestEventModel(PullRequestEventModel pullRequestEventModel) {
        this.pullRequestEventModel = pullRequestEventModel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CommentEventModel getComment() {
        return comment;
    }

    public void setComment(CommentEventModel comment) {
        this.comment = comment;
    }
}
