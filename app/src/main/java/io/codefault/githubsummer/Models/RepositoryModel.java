package io.codefault.githubsummer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yashladha on 9/12/17.
 */

public class RepositoryModel implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("full_name")
    @Expose
    private String fullName;

    @SerializedName("private")
    @Expose
    private boolean privateFlag;

    @SerializedName("fork")
    @Expose
    private boolean forkedFlag;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("owner")
    @Expose
    private OwnerModel owner;

    @SerializedName("stargazers_count")
    @Expose
    private int starCount;

    @SerializedName("watchers_count")
    @Expose
    private int watchCount;

    @SerializedName("language")
    @Expose
    private String majorLanguage;

    public OwnerModel getOwner() {
        return owner;
    }

    public void setOwner(OwnerModel owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isPrivateFlag() {
        return privateFlag;
    }

    public void setPrivateFlag(boolean privateFlag) {
        this.privateFlag = privateFlag;
    }

    public boolean isForkedFlag() {
        return forkedFlag;
    }

    public void setForkedFlag(boolean forkedFlag) {
        this.forkedFlag = forkedFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public int getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(int watchCount) {
        this.watchCount = watchCount;
    }

    public String getMajorLanguage() {
        return majorLanguage;
    }

    public void setMajorLanguage(String majorLanguage) {
        this.majorLanguage = majorLanguage;
    }
}
