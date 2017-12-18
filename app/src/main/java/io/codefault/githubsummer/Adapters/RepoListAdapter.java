package io.codefault.githubsummer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.codefault.githubsummer.Fragments.Repo;
import io.codefault.githubsummer.IssueElab;
import io.codefault.githubsummer.Models.RepoModel;
import io.codefault.githubsummer.Models.Video;
import io.codefault.githubsummer.R;
import io.codefault.githubsummer.TutorialActivity;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private Context mContext;
    private List<RepoModel> list;

    public RepoListAdapter(Context mContext ,List<RepoModel> rm){
        this.mContext = mContext;
        this.list = rm;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(mContext).inflate(R.layout.repo_index, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RepoModel temObj = list.get(position);
        Picasso.with(mContext)
                .load(temObj.getOwner().getAvatarUrl())
                .fit()
                .into(holder.avatarUrl);
        holder.name.setText(temObj.getName());
        holder.fullName.setText(temObj.getFullName());
        holder.langauge.setText(temObj.getLanguage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView avatarUrl;
        TextView name,fullName,langauge,defaultbranch;

        public ViewHolder(final View itemView){
            super(itemView);

            avatarUrl = itemView.findViewById(R.id.cv_repo_avatar);
            name = itemView.findViewById(R.id.tv_repo_name);
            fullName = itemView.findViewById(R.id.tv_repo_fullname);
            langauge = itemView.findViewById(R.id.tv_repo_language);
            defaultbranch = itemView.findViewById(R.id.tv_repo_branch);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();

                    int pos = getAdapterPosition();
                    RepoModel model = list.get(pos);
                    bundle.putSerializable("Repo", model.getLanguage());

                    if(model.getLanguage()!=null){
                        Intent intent = new Intent(mContext, TutorialActivity.class);
                        intent.putExtra("VIDEO", model.getLanguage());
                        mContext.startActivity(intent);
                    }
                    else {
                        Toast.makeText(mContext,"Language not specified",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}