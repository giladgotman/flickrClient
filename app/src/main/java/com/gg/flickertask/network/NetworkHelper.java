package com.gg.flickertask.network;

import android.util.Log;

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

/**
 * Helper module performing the REST calls to flickr api
 * Using Retrofit
 */
public class NetworkHelper {
    public static final String BASE_URL = "https://api.flickr.com/services/rest/";
    private static final String TAG = NetworkHelper.class.getSimpleName();

    private Retrofit mRetrofitApi;
    private static NetworkHelper mInstance;
    private NetworkHelper() {

    }
    public synchronized static NetworkHelper getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkHelper();
        }
        return mInstance;
    }

    public void setup() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        mRetrofitApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    /**
     * execute a REST call performing a search for photos in flickr
     * @param searchText filter text
     * @param callback callback for getting result
     * @param page the desired page (Paging)
     */
    public void getPhotoSearchResult(String searchText, Callback<PhotoSearchResult> callback, int page) {
        if (searchText == null) {
            throw new IllegalArgumentException("search text must not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("callback must not be null");
        }
        Log.d(TAG, "getPhotoSearchResult: text : " + searchText + ", page :" + page);
        SearchPhotoApi searchPhotoApi = mRetrofitApi.create(SearchPhotoApi.class);
        Call<PhotoSearchResult> call = searchPhotoApi.getPhotoSearchResult(searchText,page);
        call.enqueue(callback);
    }

}
