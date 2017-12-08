package io.codefault.githubsummer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yashladha on 8/12/17.
 */

public class UserModel {

    @SerializedName("login")
    @Expose
    private String name;

    @SerializedName("avatar_url")
    @Expose
    private String avataarUrl;

    @SerializedName("type")
    @Expose
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvataarUrl() {
        return avataarUrl;
    }

    public void setAvataarUrl(String avataarUrl) {
        this.avataarUrl = avataarUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
