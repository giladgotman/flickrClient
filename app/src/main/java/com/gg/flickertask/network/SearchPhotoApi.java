package com.gg.flickertask.network;

import com.gg.flickertask.model.PhotoSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gilad on 8/13/2016.
 */
public interface SearchPhotoApi {

    @GET("/services/rest/")
    Call<PhotoSearchResult> getPhotoSearchResult(@Query("text") String text, @Query("page") int page);
}
