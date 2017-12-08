package io.codefault.githubsummer.ApiInterfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by yashladha on 7/12/17.
 */

public interface AuthenticationService {

    @GET("/")
    Call<Void> basicAuthUser(@Header("Authorization") String authHeader);

}
