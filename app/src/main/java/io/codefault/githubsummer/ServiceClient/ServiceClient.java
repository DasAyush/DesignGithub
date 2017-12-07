package io.codefault.githubsummer.ServiceClient;

import io.codefault.githubsummer.ApiInterfaces.AuthenticationService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yashladha on 7/12/17.
 */

final public class ServiceClient {

    private static Retrofit client;

    public static void initializeClient() {
        client = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getClient() {
        return client;
    }

    public static <T> T createInterface(Class<T> tClass) {
        return client.create(tClass);
    }
}
