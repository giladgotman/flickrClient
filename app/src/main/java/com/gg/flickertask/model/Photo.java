package com.gg.flickertask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Gilad on 8/13/2016.
 */

/**
 * Photo object holding information and urls of the photo
 */
public class Photo implements Serializable{
    @SerializedName("id")
    private String mId;
    @SerializedName("owner")
    private String mOwner;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("url_s")
    private String mUrlSmallSize;
    @SerializedName("url_o")
    private String mUrlOriginalSize;
    /**
     * No args constructor for use in serialization
     *
     */
    public Photo() {
    }

    public Photo(String mId, String mOwner, String mTitle, String mUrlSmallSize, String mUrlOriginalSize) {
        this.mId = mId;
        this.mOwner = mOwner;
        this.mTitle = mTitle;
        this.mUrlSmallSize = mUrlSmallSize;
        this.mUrlOriginalSize = mUrlOriginalSize;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "mId='" + mId + '\'' +
                ", mOwner='" + mOwner + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mUrlSmallSize='" + mUrlSmallSize + '\'' +
                ", mUrlOriginalSize='" + mUrlOriginalSize + '\'' +
                '}';
    }

    public String getId() {
        return mId;
    }

    public String getOwner() {
        return mOwner;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrlSmallSize() {
        return mUrlSmallSize;
    }

    public String getUrlOriginalSize() {
        return mUrlOriginalSize;
    }
}
