package io.codefault.githubsummer.ServiceClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yashladha on 7/12/17.
 */

final public class ServiceClient {

    private static Retrofit client;
    private static String username;
    private static String authEncoded;

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

    public static void setAuthEncoded(String authEncoded) {
        ServiceClient.authEncoded = authEncoded;
    }

    public static String getAuthEncoded() {
        return authEncoded;
    }

    public static void setUsername(String username) {
        ServiceClient.username = username;
    }

    public static String getUsername() {
        return username;
    }

}
