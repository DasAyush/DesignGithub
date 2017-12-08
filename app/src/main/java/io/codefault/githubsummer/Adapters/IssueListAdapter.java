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

import io.codefault.githubsummer.Models.IssueModel;
import io.codefault.githubsummer.R;

/**
 * Created by yashladha on 8/12/17.
 */

public class IssueListAdapter extends RecyclerView.Adapter<IssueListAdapter.ViewHolder> {

    private Context mContext;
    private List<IssueModel> list;

    public IssueListAdapter(Context mContext, List<IssueModel> res) {
        this.mContext = mContext;
        this.list = res;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.issue_index, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IssueModel tempObj = list.get(position);

        Picasso.with(mContext)
                .load(tempObj.getUserModel().getAvataarUrl())
                .fit()
                .into(holder.avatarUrl);

        String issueText = tempObj.getTitle() + " #" + String.valueOf(tempObj.getNumber());
        holder.title.setText(issueText);

        holder.body.setText(tempObj.getBody());

        holder.comments.setText(String.valueOf(tempObj.getComments()));

        holder.creationDate.setText(tempObj.getCreated_at());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView avatarUrl;
        TextView body;
        TextView title;
        TextView creationDate;
        TextView comments;

        public ViewHolder(View itemView) {
            super(itemView);

            avatarUrl = itemView.findViewById(R.id.cv_issue_avatar);
            body = itemView.findViewById(R.id.tv_issue_body);
            title = itemView.findViewById(R.id.tv_issue_title);
            creationDate = itemView.findViewById(R.id.tv_issue_creation_date);
            comments = itemView.findViewById(R.id.tv_issue_no_comments);

        }
    }
}
