package io.codefault.githubsummer.ApiInterfaces;

import java.util.List;
import java.util.Map;

import io.codefault.githubsummer.Models.Feed;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by yashladha on 7/12/17.
 */

public interface FeedClient {

    @GET("/users/{username}/received_events")
    Call<List<Feed>> getFeedUser(
            @Path("username") String username,
            @QueryMap Map<String, String> query
    );

}
