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

import io.codefault.githubsummer.Models.OrgModel;
import io.codefault.githubsummer.R;

public class OrgListAdapter extends RecyclerView.Adapter<OrgListAdapter.ViewHolder> {
    private Context rmContext;
    private List<OrgModel> list;

    public OrgListAdapter(Context rmContext , List<OrgModel> om){
        this.rmContext = rmContext;
        this.list = om;

    }

    @Override
    public OrgListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(rmContext).inflate(R.layout.org_index,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(OrgListAdapter.ViewHolder holder, int position) {
     OrgModel teObj=list.get(position);
        Picasso.with(rmContext)
                .load(teObj.getAvatarUrl())
                .fit()
                .into(holder.avatarUrl);
        holder.login.setText(teObj.getLogin());
        holder.description.setText(teObj.getDescription());
        holder.url.setText(teObj.getUrl());

    }

    @Override
    public int getItemCount() {
      return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       ImageView avatarUrl;
       TextView login,description,url;
        public ViewHolder(View itemView) {
            super(itemView);
            avatarUrl =(ImageView)itemView.findViewById(R.id.cv_org_avatar);
            login=(TextView)itemView.findViewById(R.id.tv_org_name);
            description =(TextView)itemView.findViewById(R.id.tv_org_description);
            url=(TextView)itemView.findViewById(R.id.tv_org_url);
        }
    }
}