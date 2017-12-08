package io.codefault.githubsummer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yashladha on 8/12/17.
 */

public class IssueEventModel {

    @SerializedName("number")
    @Expose
    private int number;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("state")
    @Expose
    private String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
