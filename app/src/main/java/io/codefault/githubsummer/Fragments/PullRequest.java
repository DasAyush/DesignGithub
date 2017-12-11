package io.codefault.githubsummer.Fragments;

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
import io.codefault.githubsummer.Adapters.PullRequestListAdapter;
import io.codefault.githubsummer.ApiInterfaces.PullRequestClient;
import io.codefault.githubsummer.Models.PullRequestEventModel;
import io.codefault.githubsummer.R;
import io.codefault.githubsummer.ServiceClient.ServiceClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dell pc on 10-12-2017.
 */

public class PullRequest extends android.support.v4.app.Fragment {

    private Context mContext;

    @BindView(R.id.rv_pull_requests)
    RecyclerView prList;

    private PullRequestListAdapter prListAdapter;
    private List<PullRequestEventModel> pr;

    public PullRequest() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pr, container, false);
        ButterKnife.bind(this, view);
        mContext = view.getContext();

//        Intent intent = getIntent();

        pr = new ArrayList<>();
        prListAdapter = new PullRequestListAdapter(mContext, pr);
        setUpRecyclerView();

        //Bundle payload = intent.getBundleExtra("payload");
        //final IssueModel issue = (IssueModel) payload.get("Issue");

        FetchPullRequests();
        return view;
    }

    private void setUpRecyclerView() {
        prList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        prList.setLayoutManager(layoutManager);
        prList.setAdapter(prListAdapter);
    }

    private void FetchPullRequests() {
        PullRequestClient pullRequestClient = ServiceClient.createInterface(PullRequestClient.class);
        String authHeader = "Basic " + ServiceClient.getAuthEncoded();
        pullRequestClient.getUserPullRequest(authHeader,"DasAyush","Facto").enqueue(new Callback<List<PullRequestEventModel>>() {
            @Override
            public void onResponse(Call<List<PullRequestEventModel>> call, Response<List<PullRequestEventModel>> response) {
                Log.d("response", new Gson().toJson(response));
                if (response.body() != null) {
                    pr.addAll(response.body());
                    prListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<PullRequestEventModel>> call, Throwable t) {
                Log.e("Error in PR Client", t.getMessage());
            }
        });
    }

    private void MergePullRequests() {

    }

}
