package com.gg.flickertask.model;

/**
 * Created by Gilad on 8/13/2016.
 */
public class PhotoSearchResult {

    public Photos photos;

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
        this.photos = photos;
    }
}