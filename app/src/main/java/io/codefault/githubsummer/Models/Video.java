package io.codefault.githubsummer.Models;

public class Video {

    private String mId;
    private String mTitle;
    private String mChannel;
    private String mThumbnailUrl;

    public Video(String id, String title, String channel, String url){
        mId = id;
        mTitle = title;
        mChannel = channel;
        mThumbnailUrl = url;
    }

    public String getVideoId(){
        return mId;
    }

    public String getVideoTitle(){
        return mTitle;
    }

    public String getVideoChannel(){
        return mChannel;
    }

    public String getVideoThumbnailUrl(){
        return mThumbnailUrl;
    }

}
