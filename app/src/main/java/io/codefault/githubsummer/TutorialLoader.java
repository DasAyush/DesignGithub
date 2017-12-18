package io.codefault.githubsummer;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

import io.codefault.githubsummer.Models.Video;

public class TutorialLoader extends AsyncTaskLoader<List<Video>> {
    private static final String LOG_TAG = TutorialLoader.class.getName();
    private String mUrl;

    public TutorialLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "test: onStartLoading() called.");
        forceLoad();
    }

    @Override
    public List<Video> loadInBackground() {
        Log.i(LOG_TAG, "test: loadInBackground() called.");
        if(mUrl == null)
            return null;

        return TutorialUtils.fetchVideoData(mUrl);
    }
}
