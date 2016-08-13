package com.gg.flickertask.network;

import com.gg.flickertask.model.PhotoSearchResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gilad on 8/13/2016.
 */
public class NetworkHelper {
    public static final String BASE_URL = "https://api.flickr.com/services/rest/";
    private static final String TAG = NetworkHelper.class.getSimpleName();

    private Retrofit mApiService;

    public void setup() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        mApiService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public void getPhotoSearchResult(String searchText, Callback<PhotoSearchResult> callback) {
        if (searchText == null) {
            throw new IllegalArgumentException("search text must not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        SearchPhotoApi searchPhotoApi = mApiService.create(SearchPhotoApi.class);
        Call<PhotoSearchResult> call = searchPhotoApi.getPhotoSearchResult(searchText);
        call.enqueue(callback);
    }

}
