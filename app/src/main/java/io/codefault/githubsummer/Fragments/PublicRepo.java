package io.codefault.githubsummer.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import io.codefault.githubsummer.Adapters.PublicRepoAdapter;
import io.codefault.githubsummer.ApiInterfaces.PublicRepoClient;
import io.codefault.githubsummer.Models.PublicRepoModel;
import io.codefault.githubsummer.R;
import io.codefault.githubsummer.ServiceClient.ServiceClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicRepo extends Fragment {

    private Context pContext;

    @BindView(R.id.rv_public) RecyclerView publiclist;

    private PublicRepoAdapter publicRepoAdapter;
    private List<PublicRepoModel> publicRepoModel;

    public PublicRepo(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_public_repo,container,false);
        ButterKnife.bind(this, view);
        pContext = view.getContext();

        publicRepoModel = new ArrayList<>();
        publicRepoAdapter = new PublicRepoAdapter(pContext,publicRepoModel);
        setUpRecyclerView();

        FetchPublicRepo();
        return view;



    }

    private void setUpRecyclerView() {
        publiclist.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(pContext);
        publiclist.setLayoutManager(layoutManager);
        publiclist.setAdapter(publicRepoAdapter);
    }

    public  void FetchPublicRepo(){
        PublicRepoClient publicRepoClient = ServiceClient.createInterface(PublicRepoClient.class);
        String authHeader = "Basic " + ServiceClient.getAuthEncoded();
        publicRepoClient.getPublic(authHeader).enqueue(new Callback<List<PublicRepoModel>>() {
            @Override
            public void onResponse(Call<List<PublicRepoModel>> call, Response<List<PublicRepoModel>> response) {
                Log.d("response", new Gson().toJson(response));
                if(response.body()!=null){
                    publicRepoModel.addAll(response.body());
                    publicRepoAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<List<PublicRepoModel>> call, Throwable t) {
                Log.e("Error in Public Repo", t.getMessage());

            }
        });


    }
} 