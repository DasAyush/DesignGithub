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

import io.codefault.githubsummer.Models.RepoModel;
import io.codefault.githubsummer.R;

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
                .load(temObj.getAvatarUrl())
                .fit()
                .into(holder.avatarUrl);
        holder.name.setText(temObj.getName());
        holder.fullName.setText(temObj.getFullName());
        holder.langauge.setText(temObj.getLanguage());
        holder.defaultbranch.setText(temObj.getDefaultBranch());

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

            avatarUrl = (ImageView)itemView.findViewById(R.id.cv_repo_avatar);
            name = (TextView)itemView.findViewById(R.id.tv_repo_name);
            fullName = (TextView)itemView.findViewById(R.id.tv_repo_fullname);
            langauge = (TextView)itemView.findViewById(R.id.tv_repo_language);
            defaultbranch = (TextView)itemView.findViewById(R.id.tv_repo_branch);

        }
    }
}