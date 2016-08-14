package com.gg.flickertask.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gilad on 8/13/2016.
 */

/**
 * Result object returned from flickr api
 * Holds the photos list and information
 */
public class PhotoSearchResult {

    @SerializedName("photos")
    public Photos mPhotos;

    /**
     * No args constructor for use in serialization
     *
     */
    public PhotoSearchResult() {
    }

    /**
     *
     * @param photos
     */
    public PhotoSearchResult(Photos photos) {
        this.mPhotos = photos;
    }

    @Override
    public String toString() {
        return "PhotoSearchResult{" +
                "mPhotos=" + mPhotos +
                '}';
    }
}