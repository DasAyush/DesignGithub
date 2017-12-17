package io.codefault.githubsummer.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.codefault.githubsummer.Models.Video;
import io.codefault.githubsummer.R;

public class TutorialAdapter extends RecyclerView.Adapter<TutorialAdapter.MyViewHolder> {

    private List<Video> mVideos;
    final private TutorialItemClickHandler mOnClickListener;


    public TutorialAdapter(List<Video> videos, TutorialItemClickHandler listener) {
        mVideos = videos;
        mOnClickListener = listener;
    }

    public interface TutorialItemClickHandler{
        public void onListItemClick(Video clickedVideo);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorial_list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Context context = holder.mView.getContext();

        Video video = mVideos.get(position);
        holder.mVideo = video;
        holder.mTitle.setText(video.getVideoTitle());
        holder.mChannel.setText(video.getVideoChannel());

        Picasso.with(context)
                .load(video.getVideoThumbnailUrl())
                .into(holder.mPoster);

        holder.mView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mOnClickListener.onListItemClick(holder.mVideo);
            }
        });
    }

    public int getItemCount() {
        return mVideos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final View mView;
        public Video mVideo;
        public ImageView mPoster;
        public TextView mTitle;
        public TextView mChannel;

        public MyViewHolder(View view){
            super(view);
            mView = view;
            mPoster = (ImageView) view.findViewById(R.id.video_thumbnail);
            mTitle = (TextView) view.findViewById(R.id.video_title);
            mChannel = (TextView) view.findViewById(R.id.video_channel);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onListItemClick(mVideo);
        }
    }
    public void setVideoData(List<Video> videos){
        mVideos = videos;
        notifyDataSetChanged();
    }
}
