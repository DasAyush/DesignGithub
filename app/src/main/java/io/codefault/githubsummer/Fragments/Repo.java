package io.codefault.githubsummer.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.codefault.githubsummer.Adapters.RepoListAdapter;
import io.codefault.githubsummer.ApiInterfaces.RepoClient;
import io.codefault.githubsummer.Models.RepoModel;
import io.codefault.githubsummer.R;
import io.codefault.githubsummer.ServiceClient.ServiceClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repo extends android.support.v4.app.Fragment {
    private Context mContext;

    @BindView(R.id.rv_repo) RecyclerView repoList;

    private RepoListAdapter repoListAdapter;
    private List<RepoModel> repoModel;

    public Repo(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_repo ,container,false);
        ButterKnife.bind(this, view);
        mContext = view.getContext();

        repoModel = new ArrayList<>();
        repoListAdapter = new RepoListAdapter(mContext,repoModel);
        setUpRecyclerView();

        FetchRepo();
        return view;

    }



    private void setUpRecyclerView() {
        repoList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        repoList.setLayoutManager(layoutManager);
        repoList.setAdapter(repoListAdapter);
    }

    private void FetchRepo() {
        RepoClient repoClient = ServiceClient.createInterface(RepoClient.class);
        String authHeader = "Basic " + ServiceClient.getAuthEncoded();
        repoClient.getRepo(authHeader).enqueue(new Callback<List<RepoModel>>() {
            @Override
            public void onResponse(Call<List<RepoModel>> call, Response<List<RepoModel>> response) {
                Log.d("response", new Gson().toJson(response));
                if(response.body()!=null){
                    repoModel.addAll(response.body());
                    repoListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<RepoModel>> call, Throwable t) {
                Log.e("Error in Repo Client", t.getMessage());
            }
        });
    }
}