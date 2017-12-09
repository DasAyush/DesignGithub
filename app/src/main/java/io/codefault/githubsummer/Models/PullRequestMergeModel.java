package io.codefault.githubsummer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dell pc on 09-12-2017.
 */

public class PullRequestMergeModel {

    @SerializedName("sha")
    @Expose
    private String sha;

    @SerializedName("merged")
    @Expose
    private String merged;

    @SerializedName("message")
    @Expose
    private String message;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getMerged() {
        return merged;
    }

    public void setMerged(String merged) {
        this.merged = merged;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
