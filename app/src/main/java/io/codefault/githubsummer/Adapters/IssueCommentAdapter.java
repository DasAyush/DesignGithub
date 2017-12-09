package io.codefault.githubsummer.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.codefault.githubsummer.Models.IssueCommentModel;
import io.codefault.githubsummer.R;

/**
 * Created by yashladha on 9/12/17.
 */

public class IssueCommentAdapter extends RecyclerView.Adapter<IssueCommentAdapter.ViewHolder> {

    private Context mContext;
    private List<IssueCommentModel> comments;

    public IssueCommentAdapter(Context baseContext, List<IssueCommentModel> comments) {
        this.mContext = baseContext;
        this.comments = comments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_index, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IssueCommentModel tempObj = comments.get(position);

        Picasso.with(mContext)
                .load(tempObj.getUser().getAvataarUrl())
                .fit()
                .into(holder.avatar);

        holder.commentBody.setText(tempObj.getBody());
        holder.creationDate.setText(tempObj.getCreatedAt());
        holder.userName.setText(tempObj.getUser().getName());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView commentBody;
        TextView creationDate;
        TextView userName;

        public ViewHolder(View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.cv_comment_user);
            commentBody = itemView.findViewById(R.id.tv_comment_body);
            creationDate = itemView.findViewById(R.id.tv_comment_creation_date);
            userName = itemView.findViewById(R.id.tv_comment_user_name);
        }
    }
}
