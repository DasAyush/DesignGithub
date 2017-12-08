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
import io.codefault.githubsummer.Adapters.IssueListAdapter;
import io.codefault.githubsummer.ApiInterfaces.IssueClient;
import io.codefault.githubsummer.Models.IssueModel;
import io.codefault.githubsummer.R;
import io.codefault.githubsummer.ServiceClient.ServiceClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Issue extends Fragment {

    private Context mContext;

    @BindView(R.id.rv_issues) RecyclerView issueList;

    private IssueListAdapter adapter;
    private List<IssueModel> res;

    public Issue() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_issue, container, false);
        ButterKnife.bind(this, view);
        mContext = view.getContext();

        res = new ArrayList<>();
        adapter = new IssueListAdapter(mContext, res);
        setUpRecyclerView();

        FetchIssues();
        return view;
    }

    private void setUpRecyclerView() {
        issueList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        issueList.setLayoutManager(layoutManager);
        issueList.setAdapter(adapter);
    }

    private void FetchIssues() {
        IssueClient issueClient = ServiceClient.createInterface(IssueClient.class);
        String authHeader = "Basic " + ServiceClient.getAuthEncoded();
        issueClient.getUserIssue(authHeader).enqueue(new Callback<List<IssueModel>>() {
            @Override
            public void onResponse(Call<List<IssueModel>> call, Response<List<IssueModel>> response) {
                Log.d("response", new Gson().toJson(response));
                if (response.body() != null) {
                    res.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<IssueModel>> call, Throwable t) {
                Log.e("Error in Issue Client", t.getMessage());
            }
        });
    }

}
