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
import io.codefault.githubsummer.Adapters.OrgListAdapter;
import io.codefault.githubsummer.ApiInterfaces.OrgClient;
import io.codefault.githubsummer.Models.OrgModel;
import io.codefault.githubsummer.R;
import io.codefault.githubsummer.ServiceClient.ServiceClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Organization extends Fragment {
    private Context rmContext;

    @BindView(R.id.rv_org) RecyclerView orgList;

    private OrgListAdapter orgListAdapter;
    private List<OrgModel> orgModel;

    public Organization(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_org,container,false);
        ButterKnife.bind(this, view);
        rmContext=view.getContext();
        orgModel= new ArrayList<>();
        orgListAdapter = new OrgListAdapter(rmContext,orgModel);
        setUpRecyclerView();
        FetchOrganization();
        return view;
    }

    private void FetchOrganization() {
        OrgClient orgClient = ServiceClient.createInterface(OrgClient.class);
        String authHeader = "Basic " + ServiceClient.getAuthEncoded();
        orgClient.getOrg(authHeader).enqueue(new Callback<List<OrgModel>>() {
            @Override
            public void onResponse(Call<List<OrgModel>> call, Response<List<OrgModel>> response) {
                Log.d("response", new Gson().toJson(response));
                if(response.body()!=null){
                    orgModel.addAll(response.body());
                    orgListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<OrgModel>> call, Throwable t) {
                Log.e("Error in Org Client", t.getMessage());
            }
        });
    }

    private void setUpRecyclerView() {
        orgList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(rmContext);
        orgList.setLayoutManager(layoutManager);
        orgList.setAdapter(orgListAdapter);
    }
}