package io.codefault.githubsummer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dell pc on 09-12-2017.
 */

public class PullRequestBaseModel {

    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("ref")
    @Expose
    private String ref;

    @SerializedName("sha")
    @Expose
    private String sha;

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("repo")
    @Expose
    private String repo;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
