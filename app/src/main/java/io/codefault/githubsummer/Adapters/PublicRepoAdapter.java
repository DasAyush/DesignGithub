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

import io.codefault.githubsummer.Models.PublicRepoModel;
import io.codefault.githubsummer.R;

public class PublicRepoAdapter extends RecyclerView.Adapter<PublicRepoAdapter.ViewHolder>{

    private Context pContext;
    private List<PublicRepoModel> list;

    public PublicRepoAdapter(Context pContext , List<PublicRepoModel> pr){
        this.pContext = pContext;
        this.list = pr;
    }

    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(pContext).inflate(R.layout.public_repo_index,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public  void onBindViewHolder(ViewHolder holder , int position){
        PublicRepoModel teObj = list.get(position);
        Picasso.with(pContext)
                .load(teObj.getOwner().getAvatarUrl())
                .fit()
                .into(holder.avatarUrl);
        holder.name.setText(teObj.getName());
        holder.fullName.setText(teObj.getFullName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarUrl;
        TextView name ,fullName;
        public ViewHolder(View itemView) {
            super(itemView);

            avatarUrl = (ImageView)itemView.findViewById(R.id.cv_public_avatar);
            name = (TextView)itemView.findViewById(R.id.tv_public_repo_name);
            fullName =(TextView)itemView.findViewById(R.id.tv_public_fullname);


        }
    }
}