package io.codefault.githubsummer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yashladha on 8/12/17.
 */

public class IssueModel implements Serializable {

    @SerializedName("number")
    @Expose
    private int number;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("user")
    @Expose
    private UserModel userModel;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("repository")
    @Expose
    private RepositoryModel repository;

    @SerializedName("locked")
    @Expose
    private boolean locked;

    @SerializedName("comments")
    @Expose
    private int comments;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    @SerializedName("closed_at")
    @Expose
    private String closed_at;

    @SerializedName("author_association")
    @Expose
    private String authorStat;

    @SerializedName("body")
    @Expose
    private String body;

    public RepositoryModel getRepository() {
        return repository;
    }

    public void setRepository(RepositoryModel repository) {
        this.repository = repository;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public String getAuthorStat() {
        return authorStat;
    }

    public void setAuthorStat(String authorStat) {
        this.authorStat = authorStat;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
