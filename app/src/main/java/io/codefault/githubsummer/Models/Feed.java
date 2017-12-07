package io.codefault.githubsummer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yashladha on 7/12/17.
 */

public class Feed {

    @SerializedName("type")
    private String Type;

    @SerializedName("actor")
    @Expose
    private Actor actor;

    @SerializedName("public")
    @Expose
    private Boolean scope;

    @SerializedName("created_at")
    @Expose
    private String creationTime;

    @SerializedName("payload")
    @Expose
    private PayLoadModel payLoadModel;

    @SerializedName("repo")
    @Expose
    private RepoModel repoModel;

    public RepoModel getRepoModel() {
        return repoModel;
    }

    public void setRepoModel(RepoModel repoModel) {
        this.repoModel = repoModel;
    }

    public PayLoadModel getPayLoadModel() {
        return payLoadModel;
    }

    public void setPayLoadModel(PayLoadModel payLoadModel) {
        this.payLoadModel = payLoadModel;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Boolean getScope() {
        return scope;
    }

    public void setScope(Boolean scope) {
        this.scope = scope;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return Type + "/" + creationTime + "/" + actor.getLogin();
    }
}
