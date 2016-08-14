package com.gg.flickertask.network;

import android.util.Log;

import com.gg.flickertask.model.PhotoSearchResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
    private static final String TAG = NetworkHelper.class.getSimpleName();
    public static final String BASE_URL = "https://api.flickr.com";
    public static final String API_KEY = "07985c2a8548dc02ca09c5161ae25512";

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
        Interceptor clientInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().
                        addQueryParameter("method","flickr.photos.search").
                        addQueryParameter("sort","interestingness-desc").
                        addQueryParameter("extras", "url_t,url_c,date_taken,owner_name,views").
                        addQueryParameter("api_key", API_KEY).
                        addQueryParameter("format","json").
                        addQueryParameter("nojsoncallback","1").
                        build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(clientInterceptor)
                .build();


        mRetrofitApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    /**
     * execute a REST call performing a search for photos in flickr
     *
     * @param searchText filter text
     * @param callback   callback for getting result
     * @param page       the desired page (Paging)
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
        Call<PhotoSearchResult> call = searchPhotoApi.getPhotoSearchResult(searchText, page);
        call.enqueue(callback);
    }

}
