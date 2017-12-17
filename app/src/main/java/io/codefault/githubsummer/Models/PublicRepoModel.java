package io.codefault.githubsummer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublicRepoModel {

    @SerializedName("name")
    @Expose

    private String name;

    @SerializedName("full_name")
    @Expose
    private String fullName;

    @SerializedName("owner")
    @Expose

    private OwnerModel owner;

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

    public OwnerModel getOwner() {
        return owner;
    }

    public void setOwner(OwnerModel owner) {
        this.owner = owner;
    }
}