package io.codefault.githubsummer.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.codefault.githubsummer.ApiInterfaces.IssueClient;
import io.codefault.githubsummer.ApiInterfaces.PullRequestClient;
import io.codefault.githubsummer.Fragments.PullRequest;
import io.codefault.githubsummer.IssueElab;
import io.codefault.githubsummer.Models.IssueModel;
import io.codefault.githubsummer.Models.PullRequestEventModel;
import io.codefault.githubsummer.Models.PullRequestMergeModel;
import io.codefault.githubsummer.R;
import io.codefault.githubsummer.ServiceClient.ServiceClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dell pc on 10-12-2017.
 */

public class PullRequestListAdapter extends RecyclerView.Adapter<PullRequestListAdapter.ViewHolder>{

    private Context mContext;
    private List<PullRequestEventModel> prList;
    private String prNumber;

    public PullRequestListAdapter(Context mContext, List<PullRequestEventModel> res) {
        this.mContext = mContext;
        this.prList = res;
    }

    @Override
    public PullRequestListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pr_index, parent, false);
        return new PullRequestListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PullRequestListAdapter.ViewHolder holder, int position) {
        PullRequestEventModel prObject = prList.get(position);

        Picasso.with(mContext)
                .load(prObject.getUser().getAvatarUrl())
                .fit()
                .into(holder.prAvatarUrl);

        prNumber = String.valueOf(prObject.getNumber());

        String issueText = prObject.getTitle() + " #" + prNumber;

        holder.prTitle.setText(issueText);

        if(prObject.getBody()!=null)
            holder.prBody.setText(prObject.getBody());
        else
            holder.prBody.setText("No description provided");

        holder.prOpen.setText("opened on ");

        holder.prCreationDate.setText(prObject.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return prList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView prAvatarUrl;
        TextView prBody;
        TextView prTitle;
        TextView prCreationDate;
        TextView prOpen;

        public ViewHolder(final View itemView) {
            super(itemView);

            prAvatarUrl = itemView.findViewById(R.id.cv_pr_avatar);
            prBody = itemView.findViewById(R.id.tv_pr_body);
            prTitle = itemView.findViewById(R.id.tv_pr_title);
            prCreationDate = itemView.findViewById(R.id.tv_pr_creation_date);
            prOpen = itemView.findViewById(R.id.tv_pr_open);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();

                    final int pos = getAdapterPosition();
                    mergePrDialog(pos);
                }
            });

        }

        public void removeAt(int position) {
            prList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, prList.size());
            Toast.makeText(mContext,"PR Merged",Toast.LENGTH_SHORT).show();
        }

        public void mergePrDialog(final int pos){
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Merge");
            builder.setMessage("Are you sure you want to merge " + prNumber+ "?");

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(dialogInterface!=null)
                        dialogInterface.dismiss();
                }
            });

            builder.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    final PullRequestClient pullRequestClient = ServiceClient.createInterface(PullRequestClient.class);

                    pullRequestClient.mergePullRequest("Basic " + ServiceClient.getAuthEncoded(),
                            "DasAyush","Facto","8").enqueue(new Callback<PullRequestMergeModel>() {
                        @Override
                        public void onResponse(Call<PullRequestMergeModel> call, Response<PullRequestMergeModel> response) {
                            Log.d("response", new Gson().toJson(response));
                            if (response.body() != null) {
                            }
                        }

                        @Override
                        public void onFailure(Call<PullRequestMergeModel> call, Throwable t) {
                            Log.e("Error in PR Client", t.getMessage());
                            //Toast.makeText(PullRequestListAdapter.this,"PR Merge Failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                    removeAt(pos);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        }
    }


