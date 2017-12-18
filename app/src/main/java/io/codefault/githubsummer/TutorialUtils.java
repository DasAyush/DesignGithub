package io.codefault.githubsummer;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import io.codefault.githubsummer.Models.Video;

public class TutorialUtils {

    private static final String LOG_TAG = TutorialUtils.class.getSimpleName();

    private TutorialUtils(){
    }

    private static List<Video> extractFeatureFromJson(String videoJson){
        List<Video> videos = new ArrayList<>();

        try{
            JSONObject jsonObject = new JSONObject(videoJson);
            JSONArray results = jsonObject.getJSONArray("items");

            for(int i=0;i<results.length();i++){
                JSONObject currentVideo = results.getJSONObject(i);

                JSONObject getID = currentVideo.getJSONObject("id");
                String id = getID.getString("videoId");

                JSONObject others = currentVideo.getJSONObject("snippet");
                String name = others.getString("title");
                String channelName = others.getString("channelTitle");

                JSONObject thumbnails = others.getJSONObject("thumbnails");
                JSONObject image = thumbnails.getJSONObject("default");
                String url = image.getString("url");

                Video video = new Video(id, name, channelName, url);
                videos.add(video);
            }
        }catch (JSONException e){

        }
        return videos;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if(url==null)
            return jsonResponse;

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            if(urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else{
                Log.e(LOG_TAG, "Error Response Code: " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retreiving the earthquake json results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static List<Video> fetchVideoData(String requestUrl){
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try{
            jsonResponse = makeHttpRequest(url);

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Video> videos = extractFeatureFromJson(jsonResponse);
        return videos;
    }

}
