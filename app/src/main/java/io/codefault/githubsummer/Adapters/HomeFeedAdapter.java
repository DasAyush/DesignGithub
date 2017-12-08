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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import io.codefault.githubsummer.Models.CommentModel;
import io.codefault.githubsummer.Models.Feed;
import io.codefault.githubsummer.R;

/**
 * Created by yashladha on 7/12/17.
 */

public class HomeFeedAdapter extends RecyclerView.Adapter<HomeFeedAdapter.ViewHolder> {

    private Context mContext;
    private List<Feed> feeds;

    public HomeFeedAdapter(Context mContext, List<Feed> feeds) {
        this.mContext = mContext;
        this.feeds = feeds;
    }

    @Override
    public HomeFeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.feed_index, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeFeedAdapter.ViewHolder holder, int position) {
        Feed tempFeed = feeds.get(position);

        Picasso.with(mContext)
                .load(tempFeed.getActor().getAvatarUrl())
                .fit()
                .into(holder.avataar);


        String type = tempFeed.getType();
        String concTypeEvent = "";
        switch (type) {
            case "PullRequestReviewCommentEvent":
                concTypeEvent = "Commented on the pull request #" + String.valueOf(tempFeed.getPayLoadModel().getPullRequestModel().getNumber());
                break;
            case "IssuesEvent":
                concTypeEvent = "Opened an issue #" + String.valueOf(tempFeed.getPayLoadModel().getIssueModel().getNumber());
                break;
            case "IssueCommentEvent":
                concTypeEvent = "Commented on issue #" + String.valueOf(tempFeed.getPayLoadModel().getIssueModel().getNumber());
                break;
            case "PullRequestEvent":
                concTypeEvent = "a pull request #" + String.valueOf(tempFeed.getPayLoadModel().getPullRequestModel().getNumber());
                String createdStatus = tempFeed.getPayLoadModel().getAction();
                char[] tempArr = createdStatus.toCharArray();
                tempArr[0] = (char)(tempArr[0] - 32);

                concTypeEvent = generateString(tempArr) + concTypeEvent;
                break;
            case "PushEvent":
                concTypeEvent = "Pushed to " + tempFeed.getRepoModel().getName();
                break;
        }
        String titleText = tempFeed.getActor().getLogin() + " " + concTypeEvent;
        holder.title.setText(titleText);

        CommentModel comment = tempFeed.getPayLoadModel().getComment();
        if (comment != null) {
            holder.description.setText(comment.getBody());
        }

        String lastingTime = getStatusTimeStamp(tempFeed.getCreationTime());
        holder.timeSpent.setText(lastingTime);
    }

    private String generateString(char[] tempArr) {
        StringBuilder temp = new StringBuilder();
        for (char x : tempArr) {
            temp.append(x);
        }
        return temp.toString();
    }

    private String getStatusTimeStamp(String creatTime) {
        String lastTime = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date creationDate = format.parse(creatTime);
            Date currentDate = format.parse(format.format(new Date()));

            int elapsedSec = creationDate.getSeconds() - currentDate.getSeconds();
            if (elapsedSec >= 0) {
                lastTime = String.valueOf(elapsedSec) + " sec";
            } else {
                int elapsedMin = creationDate.getMinutes() - currentDate.getMinutes();
                if (elapsedMin > 0) {
                    lastTime = String.valueOf(elapsedMin) + " min";
                } else {
                    int elapsedHours = creationDate.getHours() - currentDate.getHours();
                    if (elapsedHours > 0) {
                        lastTime = String.valueOf(elapsedHours) + " hrs";
                    } else {
                        int elapsedDays = creationDate.getDay() - currentDate.getDay();
                        if (elapsedDays > 0) {
                            lastTime = String.valueOf(elapsedDays) + " days";
                        }
                    }
                }
            }
        } catch (ParseException e) {
            Log.e("getStatusTimeStamp", "Unable to parse the timeStamp");
        }
        return lastTime;
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView avataar;
        TextView title;
        TextView description;
        TextView timeSpent;

        public ViewHolder(View itemView) {
            super(itemView);
            avataar = itemView.findViewById(R.id.iv_avatar);
            title = itemView.findViewById(R.id.tv_feed_title);
            description = itemView.findViewById(R.id.tv_feed_description);
            timeSpent = itemView.findViewById(R.id.tv_time_elapsed);
        }
    }
}
