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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.codefault.githubsummer.Adapters.HomeFeedAdapter;
import io.codefault.githubsummer.ApiInterfaces.FeedClient;
import io.codefault.githubsummer.Models.Feed;
import io.codefault.githubsummer.R;
import io.codefault.githubsummer.ServiceClient.ServiceClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private Context mContext;

    @BindView(R.id.rv_home_feed)
    RecyclerView feedList;

    private List<Feed> obj;
    private HomeFeedAdapter adapter;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        mContext = view.getContext();
        obj = new ArrayList<>();
        adapter = new HomeFeedAdapter(mContext, obj);

        setUpFeed();
        getFeed();

        return view;
    }

    private void getFeed() {
        FeedClient client = ServiceClient.createInterface(FeedClient.class);

        Map<String, String> query = new HashMap<>();

        query.put("page", "0");

        client.getFeedUser(ServiceClient.getUsername(), query).enqueue(
                new Callback<List<Feed>>() {
                    @Override
                    public void onResponse(Call<List<Feed>> call, Response<List<Feed>> response) {
                        Toast.makeText(mContext, "True", Toast.LENGTH_SHORT).show();
                        if (obj == null || response.body() == null) {
                            Log.d("Response", "Empty Response");
                        } else {
                            obj.addAll(response.body());
                            Log.d("Response", "Response is not empty");
                            for (Feed item : obj) {
                                if (item.getPayLoadModel().getComment() != null)
                                    Log.d("Body", "Contains Comment");
                                else
                                    Log.d("Body", "Null response");
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Feed>> call, Throwable t) {
                        Toast.makeText(mContext, "False", Toast.LENGTH_SHORT).show();
                        Log.d("Error", t.getMessage());
                    }
                }
        );
    }

    private void setUpFeed() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        feedList.setLayoutManager(layoutManager);
        feedList.setHasFixedSize(true);
        feedList.setAdapter(adapter);
    }

}
