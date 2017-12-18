package io.codefault.githubsummer;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.codefault.githubsummer.Adapters.TutorialAdapter;
import io.codefault.githubsummer.Models.Video;

public class TutorialActivity extends AppCompatActivity implements TutorialAdapter.TutorialItemClickHandler,LoaderManager.LoaderCallbacks<List<Video>>{

    private static final int TUTORIAL_LOADER_ID = 1;
    private List<Video> videoList;
    private TutorialAdapter mTutorialAdapter;
    private RecyclerView mRecyclerView;
    private String mLanguage;

    public static final String TUTORIAL_BASE_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        mLanguage = null;
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("VIDEO")){
                mLanguage = intent.getStringExtra("VIDEO");
            }
        }

        videoList = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_tutorials);
        mTutorialAdapter = new TutorialAdapter(videoList, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        mRecyclerView.setAdapter(mTutorialAdapter);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(TUTORIAL_LOADER_ID, null, this);
    }

    @Override
    public void onListItemClick(Video clickedVideo) {
        String url = "https://www.youtube.com/watch?v=".concat(clickedVideo.getVideoId());
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private String createTutorialUrl() {
        String url = TUTORIAL_BASE_URL + mLanguage + "&maxResults=25&key=AIzaSyAyr78u6vi9oZKu4Wugm3lpOdD8xd0cLr0";
        return url;
    }

    @Override
    public Loader<List<Video>> onCreateLoader(int i, Bundle bundle) {
        return new TutorialLoader(this, createTutorialUrl());
    }

    @Override
    public void onLoadFinished(Loader<List<Video>> loader, List<Video> videos) {
        if(videos != null){
            mRecyclerView.setVisibility(View.VISIBLE);
            mTutorialAdapter.setVideoData(videos);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Video>> loader) {

    }
}
