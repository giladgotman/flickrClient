package com.gg.flickertask.network;

import com.gg.flickertask.model.PhotoSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gilad on 8/13/2016.
 */
public interface SearchPhotoApi {

    String SEARCH_API_CONSTANT_PARAMETERS = "?method=flickr.photos.search&api_key=b00821b0a71dbe67352a64b74e8ae3d9&format=json&nojsoncallback=1&extras=url_s%2Curl_o";

    @GET(SEARCH_API_CONSTANT_PARAMETERS)
    Call<PhotoSearchResult> getPhotoSearchResult(@Query("text") String text);


}
