package com.gg.flickertask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Gilad on 8/13/2016.
 */

/**
 * Photo object holding information and urls of the photo.
 */
public class Photo implements Serializable {
    @SerializedName("id")
    private String mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("url_t")
    private String mUrlSmallSize;
    @SerializedName("url_c")
    private String mUrlLargeSize;
    @SerializedName("url_o")
    private String mUrlOrigianlSize;
    @SerializedName("ownername")
    private String mOwenerName;
    @SerializedName("datetaken")
    private String mDateTaken;
    @SerializedName("views")
    private String mViews;

    /**
     * No args constructor for use in serialization.
     *
     */
    public Photo() {
    }

    public Photo(String mId, String mTitle, String mUrlSmallSize,
                 String mUrlLargeSize, String mUrlOrigianlSize,
                 String mOwenerName, String mDateTaken, String mViews) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mUrlSmallSize = mUrlSmallSize;
        this.mUrlLargeSize = mUrlLargeSize;
        this.mUrlOrigianlSize = mUrlOrigianlSize;
        this.mOwenerName = mOwenerName;
        this.mDateTaken = mDateTaken;
        this.mViews = mViews;
    }


    @Override
    public String toString() {
        return "Photo{" +
                "mId='" + mId + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mUrlSmallSize='" + mUrlSmallSize + '\'' +
                ", mUrlLargeSize='" + mUrlLargeSize + '\'' +
                ", mOwenerName='" + mOwenerName + '\'' +
                ", mDateTaken='" + mDateTaken + '\'' +
                ", mViews='" + mViews + '\'' +
                '}';
    }

    public String getId() {
        return mId;
    }


    public String getTitle() {
        return mTitle;
    }

    public String getUrlSmallSize() {
        return mUrlSmallSize;
    }

    public String getUrlLargeSize() {
        return mUrlLargeSize;
    }

    public String getUrlOriginalSize() {
        return mUrlOrigianlSize;
    }

    public String getOwenerName() {
        return mOwenerName;
    }

    public String getDateTaken() {
        return mDateTaken;
    }

    public String getViews() {
        return mViews;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
